package com.n3rditorium.smartbedside.system.wifi;

<<<<<<< Updated upstream
import com.n3rditorium.smartbedside.core.BaseContract;
=======
import com.n3rditorium.common.mvp.BaseContract;
>>>>>>> Stashed changes

public interface WifiInfoContract {

   interface View extends BaseContract.View {

      void displaySSID(String ssid);

      void displaySignalStrength(int signalStrength);
   }
}
