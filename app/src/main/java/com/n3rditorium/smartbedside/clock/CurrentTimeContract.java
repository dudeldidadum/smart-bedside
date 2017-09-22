package com.n3rditorium.smartbedside.clock;

import com.n3rditorium.smartbedside.core.BaseContract;

public interface CurrentTimeContract {

   interface View extends BaseContract.View {

      void displayCurrentDate(String line1, String line2);

      void displayCurrentTime(String text);
   }
}
