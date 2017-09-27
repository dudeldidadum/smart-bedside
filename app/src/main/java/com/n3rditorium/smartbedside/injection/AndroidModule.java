package com.n3rditorium.smartbedside.injection;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.view.WindowManager;

import com.n3rditorium.smartbedside.core.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {

   private final BaseApplication application;
   private final Context context;

   public AndroidModule(BaseApplication application) {
      this.application = application;
      this.context = application.getApplicationContext();
   }

   @Provides
   public WifiManager provideWifiManager(Context context) {
      return (WifiManager) context.getApplicationContext()
            .getSystemService(Context.WIFI_SERVICE);
   }

   @Provides
   @Singleton
   BaseApplication provideBaseApplication() {
      return application;
   }

   @Provides
   ConnectivityManager provideConnectivityManager(Context context) {
      return (ConnectivityManager) context.getApplicationContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE);
   }

   @Provides
   ContentResolver provideContentResolver(Context context) {
      return context.getContentResolver();
   }

   @Provides
   @Singleton
   Context provideContext() {
      return context;
   }

   @Provides
   WindowManager provideWindowManager(Context context) {
      return (WindowManager) context.getApplicationContext()
            .getSystemService(Context.WINDOW_SERVICE);
   }
}
