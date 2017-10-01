package com.n3rditorium.smartbedside.system.brightness;

<<<<<<< Updated upstream
import com.n3rditorium.smartbedside.core.BaseContract;
=======
import com.n3rditorium.common.mvp.BaseContract;
>>>>>>> Stashed changes

public interface AdjustBrightnessContract {

   interface View extends BaseContract.View {

      void displayCurrentBrightness(int brightness);

      void hideAdjustBrightnessView();

   }
}
