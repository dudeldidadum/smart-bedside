package com.n3rditorium.smartbedside.clock;

public interface CurrentTimeContract {

   interface View {

      void displayCurrentDate(String line1, String line2);

      void displayCurrentTime(String text);
   }
}
