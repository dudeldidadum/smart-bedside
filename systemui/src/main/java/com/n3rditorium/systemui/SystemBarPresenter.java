package com.n3rditorium.smartbedside.system.wifi;

import com.n3rditorium.common.mvp.BasePresenter;
import com.n3rditorium.core.system.NetworkInfoService;
import com.n3rditorium.smartbedside.injection.Injector;

import javax.inject.Inject;

public class WifiInfoPresenter extends BasePresenter<WifiInfoContract.View> {

   @Inject
   NetworkInfoService networkInfoService;

   @Override
   protected void bindView(WifiInfoContract.View view) {
      super.bindView(view);
      Injector.getAppComponent()
            .inject(this);
      loadAndShowNetworkInfo();
   }

   private void loadAndShowNetworkInfo() {
      getView().displaySSID(networkInfoService.getSSID());
      getView().displaySignalStrength(networkInfoService.getSignalStrength());
   }
}
