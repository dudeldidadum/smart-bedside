package com.n3rditorium.smartbedside.camera;

import android.graphics.Bitmap;

import com.n3rditorium.common.mvp.BaseContract;

public interface CameraContract {

   interface View extends BaseContract.View {

      void displayImage(Bitmap bitmap);
   }
}
