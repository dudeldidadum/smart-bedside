package com.n3rditorium.smartbedside.system;

import com.n3rditorium.core.system.DisplayInfoService;
import com.n3rditorium.core.system.NetworkInfoService;
import com.n3rditorium.smartbedside.core.BasePresenter;
import com.n3rditorium.smartbedside.injection.Injector;

import javax.inject.Inject;

public class DebugInfoPresenter extends BasePresenter<DebugInfoContract.View> {

   @Inject
   DisplayInfoService displayInfoService;
   @Inject
   NetworkInfoService networkInfoService;

   @Override
   protected void bindView(DebugInfoContract.View view) {
      super.bindView(view);
      Injector.getAppComponent()
            .inject(this);
      loadAndShowisplayMetrics();
      loadAndShowNetworkInfo();
   }

   private void loadAndShowNetworkInfo() {
      StringBuilder builder = new StringBuilder();
      builder.append("Network:\n")
            .append("SSID: ")
            .append(networkInfoService.getSSID())
            .append("\n")
            .append("Signal: ")
            .append(networkInfoService.getSignalStrength())
            .append("\n")
            .append("IP: ")
            .append(networkInfoService.getIP())
            .append("\n");

      getView().showNetworkInfo(builder.toString());
   }

   private void loadAndShowisplayMetrics() {
      StringBuilder builder = new StringBuilder();
      builder.append("Display:\n")
            .append("width: ")
            .append(displayInfoService.getWidth())
            .append("px")
            .append("\n")
            .append("height: ")
            .append(displayInfoService.getHeight())
            .append("px")
            .append("\n")
            .append("density: ")
            .append(displayInfoService.getDensity());

      getView().showDisplayInfo(builder.toString());
   }
}
