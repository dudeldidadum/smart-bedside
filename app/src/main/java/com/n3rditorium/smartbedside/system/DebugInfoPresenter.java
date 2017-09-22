package com.n3rditorium.smartbedside.system;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.n3rditorium.smartbedside.core.BasePresenter;

public class DebugInfoPresenter extends BasePresenter<DebugInfoContract.View> {

   @Override
   protected void bindView(DebugInfoContract.View view) {
      super.bindView(view);

      getAndShowisplayMetrics();
   }

   private void getAndShowisplayMetrics() {
      WindowManager windowManager = (WindowManager) getView().getContext()
            .getSystemService(Context.WINDOW_SERVICE);

      DisplayMetrics metrics = new DisplayMetrics();
      windowManager.getDefaultDisplay()
            .getMetrics(metrics);

      StringBuilder builder = new StringBuilder();
      builder.append("Display:\n")
            .append("width: ")
            .append(metrics.widthPixels)
            .append("px")
            .append("\n")
            .append("height: ")
            .append(metrics.heightPixels)
            .append("px")
            .append("\n")
            .append("density: ")
            .append(metrics.density);

      getView().showDisplayInfo(builder.toString());
   }
}
