package com.n3rditorium.smartbedside.camera;

import android.graphics.Bitmap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;

import com.n3rditorium.common.mvp.BasePresenter;
import com.n3rditorium.smartbedside.injection.Injector;

import javax.inject.Inject;

import timber.log.Timber;

public class CameraPresenter extends BasePresenter<CameraContract.View> {

   @Inject
   CameraHandler cameraHandler;
   @Inject
   ImagePreprocessor imagePreprocessor;
   private Handler backgroundHandler;
   private HandlerThread backgroundThread;
   private Runnable initializeOnBackground = new Runnable() {
      @Override
      public void run() {
         Injector.getAppComponent()
               .inject(CameraPresenter.this);
         cameraHandler.initializeCamera(getView().getContext(), backgroundHandler,
               new ImageReader.OnImageAvailableListener() {

                  @Override
                  public void onImageAvailable(ImageReader imageReader) {
                     final Bitmap bitmap;
                     try (Image image = imageReader.acquireNextImage()) {
                        bitmap = imagePreprocessor.preprocessImage(image);
                     }
                     Timber.i("image is available");
                     getView().displayImage(bitmap);
                  }
               });
      }
   };

   @Override
   protected void bindView(CameraContract.View view) {
      super.bindView(view);

   }

   void init() {
      backgroundThread = new HandlerThread("BackgroundThread");
      backgroundThread.start();
      backgroundHandler = new Handler(backgroundThread.getLooper());
      backgroundHandler.post(initializeOnBackground);
   }

   @Override
   protected void unbindView() {
      super.unbindView();
      try {
         if (backgroundThread != null) {
            backgroundThread.quit();
         }
      } catch (Throwable t) {
         // close quietly
         Timber.e(t, "could not close backgroundThread");
      }
      backgroundThread = null;
      backgroundHandler = null;

      try {
         if (cameraHandler != null) {
            cameraHandler.shutDown();
         }
      } catch (Throwable t) {
         // close quietly
         Timber.e(t, "could not close cameraHandler");
      }
   }

   void takePicture() {
      if (cameraHandler == null) {
         return;
      }
      cameraHandler.takePicture();
   }
}