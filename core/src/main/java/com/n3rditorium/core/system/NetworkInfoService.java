package com.n3rditorium.core.system;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.text.format.Formatter;

public class NetworkInfoService {

   private final ConnectivityManager connectivityManager;
   private final WifiManager wifiManager;

   public NetworkInfoService(ConnectivityManager connectivityManager, WifiManager wifiManager) {
      this.connectivityManager = connectivityManager;
      this.wifiManager = wifiManager;
   }

   public String getIP() {
      final WifiInfo connectionInfo = getConnectionInfo();
      return Formatter.formatIpAddress(connectionInfo.getIpAddress());

   }

   public String getSSID() throws IllegalStateException {
      final WifiInfo connectionInfo = getConnectionInfo();
      String ssid = connectionInfo.getSSID()
            .replaceAll("\"", "");
      if (TextUtils.isEmpty(ssid)) {
         throw new IllegalStateException("SSID is empty");
      }
      return ssid;
   }

   public int getSignalStrength() {
      int rssi = wifiManager.getConnectionInfo()
            .getRssi();
      return WifiManager.calculateSignalLevel(rssi, 5);
   }

   private WifiInfo getConnectionInfo() throws IllegalStateException {
      NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
      if (networkInfo == null) {
         throw new IllegalStateException("could not get network info");
      }

      if (!networkInfo.isConnected()) {
         throw new IllegalStateException("not conntected to a network");
      }
      WifiInfo connectionInfo = wifiManager.getConnectionInfo();
      if (connectionInfo == null) {
         throw new IllegalStateException("could not retrieve connection info");
      }
      return connectionInfo;
   }
}
