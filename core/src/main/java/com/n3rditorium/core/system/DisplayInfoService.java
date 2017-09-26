package com.n3rditorium.core.system;

import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DisplayInfoService {

   private final DisplayMetrics displayMetrics;

   public DisplayInfoService(WindowManager windowManager) {
      this.displayMetrics = new DisplayMetrics();
      windowManager.getDefaultDisplay()
            .getMetrics(displayMetrics);
   }

   public float getDensity() {
      return displayMetrics.density;
   }

   public int getHeight() {
      return displayMetrics.heightPixels;
   }

   public int getWidth() {
      return displayMetrics.widthPixels;
   }
}
