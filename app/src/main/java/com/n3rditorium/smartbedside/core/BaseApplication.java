package com.n3rditorium.smartbedside.core;

import android.app.Application;

import com.n3rditorium.smartbedside.injection.Injector;

import timber.log.Timber;

public class BaseApplication extends Application {

   @Override
   public void onCreate() {
      super.onCreate();
      Injector.init(this);
      //if (BuildConfig.DEBUG) {
         Timber.plant(new Timber.DebugTree());
      //}
   }
}
