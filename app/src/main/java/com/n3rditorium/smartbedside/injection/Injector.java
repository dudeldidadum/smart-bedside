package com.n3rditorium.smartbedside.injection;

import com.n3rditorium.smartbedside.core.BaseApplication;

public final class Injector {

   private static AppComponent appComponent;

   public static AppComponent getAppComponent() {
      return appComponent;
   }

   public static void init(BaseApplication application) {
      appComponent = DaggerAppComponent.builder()
            .androidModule(new AndroidModule(application))
            .build();
   }

   private Injector() {
      //should not be instantiated
   }
}
