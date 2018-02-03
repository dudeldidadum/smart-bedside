package com.n3rditorium.smartbedside.led.brightness;

import com.google.android.things.pio.PeripheralManagerService;
import com.n3rditorium.common.mvp.BasePresenter;
import com.n3rditorium.smartbedside.injection.Injector;

import javax.inject.Inject;

public class SliderPresenter extends BasePresenter<SliderContract.View> {

   @Inject
   PeripheralManagerService managerService;

   @Override
   protected void bindView(SliderContract.View view) {
      super.bindView(view);
      Injector.getAppComponent()
            .inject(this);
   }

   public PeripheralManagerService getPeripheralManagerService() {
      //return managerService;
      return new PeripheralManagerService();
   }
}