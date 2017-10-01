package com.n3rditorium.smartbedside.system.wifi;

import com.n3rditorium.common.mvp.BaseContract;

public interface WifiInfoContract {

   interface View extends BaseContract.View {

      void displaySSID(String ssid);

      void displaySignalStrength(int signalStrength);
   }
}
