package com.n3rditorium.smartbedside.system.brightness;

import com.n3rditorium.smartbedside.core.BaseContract;

public interface AdjustBrightnessContract {

   interface View extends BaseContract.View {

      void displayCurrentBrightness(int brightness);

      void hideAdjustBrightnessView();

   }
}
