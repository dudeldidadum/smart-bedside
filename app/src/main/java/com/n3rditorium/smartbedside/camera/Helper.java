package com.n3rditorium.smartbedside.camera;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.util.Log;

import junit.framework.Assert;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Helper functions for the TensorFlow image classifier.
 */
public class Helper {

   public static final int IMAGE_SIZE = 224;
   private static final int IMAGE_MEAN = 117;
   private static final float IMAGE_STD = 1;
   private static final String LABELS_FILE = "imagenet_comp_graph_label_strings.txt";

   public static void cropAndRescaleBitmap(final Bitmap src, final Bitmap dst,
         int sensorOrientation) {
      Assert.assertEquals(dst.getWidth(), dst.getHeight());
      final float minDim = Math.min(src.getWidth(), src.getHeight());

      final Matrix matrix = new Matrix();

      // We only want the center square out of the original rectangle.
      final float translateX = -Math.max(0, (src.getWidth() - minDim) / 2);
      final float translateY = -Math.max(0, (src.getHeight() - minDim) / 2);
      matrix.preTranslate(translateX, translateY);

      final float scaleFactor = dst.getHeight() / minDim;
      matrix.postScale(scaleFactor, scaleFactor);

      // Rotate around the center if necessary.
      if (sensorOrientation != 0) {
         matrix.postTranslate(-dst.getWidth() / 2.0f, -dst.getHeight() / 2.0f);
         matrix.postRotate(sensorOrientation);
         matrix.postTranslate(dst.getWidth() / 2.0f, dst.getHeight() / 2.0f);
      }

      final Canvas canvas = new Canvas(dst);
      canvas.drawBitmap(src, matrix, null);
   }

   public static String formatResults(String[] results) {
      String resultStr;
      if (results == null || results.length == 0) {
         resultStr = "I don't know what I see.";
      } else if (results.length == 1) {
         resultStr = "I see a " + results[0];
      } else {
         resultStr = "I see a " + results[0] + " or maybe a " + results[1];
      }
      return resultStr;
   }

   public static float[] getPixels(Bitmap bitmap, int[] intValues, float[] floatValues) {
      if (bitmap.getWidth() != IMAGE_SIZE || bitmap.getHeight() != IMAGE_SIZE) {
         // rescale the bitmap if needed
         bitmap = ThumbnailUtils.extractThumbnail(bitmap, IMAGE_SIZE, IMAGE_SIZE);
      }

      bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(),
            bitmap.getHeight());

      // Preprocess the image data from 0-255 int to normalized float based
      // on the provided parameters.
      for (int i = 0; i < intValues.length; ++i) {
         final int val = intValues[i];
         floatValues[i * 3] = (((val >> 16) & 0xFF) - IMAGE_MEAN) / IMAGE_STD;
         floatValues[i * 3 + 1] = (((val >> 8) & 0xFF) - IMAGE_MEAN) / IMAGE_STD;
         floatValues[i * 3 + 2] = ((val & 0xFF) - IMAGE_MEAN) / IMAGE_STD;
      }
      return floatValues;
   }

   public static String[] readLabels(Context context) {
      AssetManager assetManager = context.getAssets();
      ArrayList<String> result = new ArrayList<>();
      try (InputStream is = assetManager.open(LABELS_FILE);
           BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
         String line;
         while ((line = br.readLine()) != null) {
            result.add(line);
         }
         return result.toArray(new String[result.size()]);
      } catch (IOException ex) {
         throw new IllegalStateException("Cannot read labels from " + LABELS_FILE);
      }
   }

   /**
    * Saves a Bitmap object to disk for analysis.
    *
    * @param bitmap The bitmap to save.
    */
   public static void saveBitmap(final Bitmap bitmap) {
      final File file =
            new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                  "tensorflow_preview.png");
      Log.d("ImageHelper",
            String.format("Saving %dx%d bitmap to %s.", bitmap.getWidth(), bitmap.getHeight(),
                  file.getAbsolutePath()));

      if (file.exists()) {
         file.delete();
      }
      try (FileOutputStream fs = new FileOutputStream(file);
           BufferedOutputStream out = new BufferedOutputStream(fs)) {
         bitmap.compress(Bitmap.CompressFormat.PNG, 99, out);
      } catch (final Exception e) {
         Log.w("ImageHelper", "Could not save image for debugging. " + e.getMessage());
      }
   }
}
