package com.n3rditorium.smartbedside.core;

import android.content.Context;

@dagger.Module
public class ApplicationModule {

   private final BaseApplication application;
   private final Context context;
   
   public ApplicationModule(BaseApplication application) {
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
