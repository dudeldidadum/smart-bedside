package com.intive.cultea.beta.core;

import android.app.Application;

import com.intive.cultea.beta.BuildConfig;

import timber.log.Timber;

public class BaseApplication extends Application {

   @Override
   public void onCreate() {
      super.onCreate();
      Injector.init(this);
      if (BuildConfig.DEBUG) {
         Timber.plant(new Timber.DebugTree());
      }
   }
}
