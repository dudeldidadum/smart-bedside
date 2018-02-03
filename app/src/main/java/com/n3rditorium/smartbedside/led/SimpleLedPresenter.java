package com.n3rditorium.smartbedside.led;

import com.n3rditorium.common.mvp.BasePresenter;
import com.n3rditorium.smartbedside.injection.Injector;

public class SimpleLedPresenter extends BasePresenter<SimpleLedContract.View> {

   @Override
   protected void bindView(SimpleLedContract.View view) {
      super.bindView(view);
      Injector.getAppComponent()
            .inject(this);
   }


}