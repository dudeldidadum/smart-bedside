package com.n3rditorium.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

public class ExternalIntentUtils {

   public static final int SETTINGS_DRAW_OVERLAY_REQUEST_CODE = 1002;
   public static final int SETTINGS_REQUEST_CODE = 1000;
   public static final int SETTINGS_WIFI_REQUEST_CODE = 1001;
   public static final int SETTINGS_WRITEABLE_REQUEST_CODE = 1003;

   private static final String TAG = ExternalIntentUtils.class.getSimpleName();

   public static void enableOverlayDrawing(Activity source) {
      if (!Settings.canDrawOverlays(source)) {

         //If the draw over permission is not available open the settings screen
         //to grant the permission.
         Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
               Uri.parse("package:" + source.getPackageName()));
         source.startActivityForResult(intent, SETTINGS_DRAW_OVERLAY_REQUEST_CODE);
      }
   }

   public static void checkWriteableSettings(Activity source) {
      if (!Settings.System.canWrite(source)) {
         Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
         intent.setData(Uri.parse("package:" + source.getPackageName()));
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         source.startActivityForResult(intent, SETTINGS_WRITEABLE_REQUEST_CODE);
      }
   }

   public static void openSettings(Activity source) {
      Intent wifiIntent = new Intent(Settings.ACTION_SETTINGS);
      if (wifiIntent.resolveActivity(source.getPackageManager()) != null) {
         source.startActivityForResult(wifiIntent, SETTINGS_REQUEST_CODE);
      } else {
         Log.e(TAG, "wifi settings not available");
      }
   }

   public static void openWifiSettings(Activity source) {
      Intent wifiIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
      if (wifiIntent.resolveActivity(source.getPackageManager()) != null) {
         source.startActivityForResult(wifiIntent, SETTINGS_WIFI_REQUEST_CODE);
      } else {
         Log.e(TAG, "settings not available");
      }
   }
}
