package com.n3rditorium.core.injection;

import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.view.WindowManager;

import com.n3rditorium.core.system.DisplayInfoService;
import com.n3rditorium.core.system.NetworkInfoService;

import dagger.Module;
import dagger.Provides;

@Module
public class SystemModule {

   @Provides
   DisplayInfoService provideDisplayInfoService(WindowManager windowManager) {
      return new DisplayInfoService(windowManager);
   }

   @Provides
   NetworkInfoService provideNetworkInfoService(ConnectivityManager connectivityManager,
         WifiManager wifiManager) {
      return new NetworkInfoService(connectivityManager, wifiManager);
   }
}
