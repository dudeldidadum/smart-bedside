package com.n3rditorium.smartbedside.injection;

import android.content.Context;

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
   @Singleton
   BaseApplication provideBaseApplication() {
      return application;
   }

   @Provides
   @Singleton
   Context provideContext() {
      return context;
   }
}
