package com.intive.cultea.beta.core;

import com.intive.cultea.beta.core.module.AppComponent;
import com.intive.cultea.beta.core.module.ApplicationModule;
import com.intive.cultea.beta.core.module.DaggerAppComponent;

public final class Injector {

   private static AppComponent appComponent;

   public static AppComponent getAppComponent() {
      return appComponent;
   }

   public static void init(BaseApplication application) {
      appComponent = DaggerAppComponent.builder()
            .applicationModule(new ApplicationModule(application))
            .build();
   }

   private Injector() {
      //should not be instantiated
   }
}
