package com.n3rditorium.systemui;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

public class SystemBarService extends Service {

   private View systemBarView;
   private WindowManager windowManager;

   public SystemBarService() {

   }

   @Override
   public IBinder onBind(Intent intent) {
      return null;
   }

   @Override
   public void onCreate() {
      super.onCreate();
      //Inflate the chat head layout we created
      systemBarView = LayoutInflater.from(this)
            .inflate(R.layout.system_bar, null);

      //Add the view to the window.
      final WindowManager.LayoutParams params =
            new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                  WindowManager.LayoutParams.WRAP_CONTENT,
                  WindowManager.LayoutParams.TYPE_STATUS_BAR,
                  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);

      //Specify the chat head position
      //Initially view will be added to top-left corner
      params.gravity = Gravity.BOTTOM | Gravity.LEFT;

      //Add the view to the window
      windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
      windowManager.addView(systemBarView, params);
   }

   @Override
   public void onDestroy() {
      super.onDestroy();
      if (systemBarView != null) {
         windowManager.removeView(systemBarView);
      }
   }
}
