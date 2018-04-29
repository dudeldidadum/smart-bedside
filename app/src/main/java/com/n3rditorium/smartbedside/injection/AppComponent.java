package com.n3rditorium.smartbedside.injection;

import android.content.Context;

import com.n3rditorium.core.injection.SystemModule;
import com.n3rditorium.core.injection.TimeModule;
import com.n3rditorium.smartbedside.camera.CameraPresenter;
import com.n3rditorium.smartbedside.clock.CurrentTimePresenter;
import com.n3rditorium.smartbedside.core.BaseApplication;
import com.n3rditorium.smartbedside.led.SimpleLedPresenter;
import com.n3rditorium.smartbedside.led.brightness.SliderPresenter;
import com.n3rditorium.smartbedside.system.DebugInfoPresenter;
import com.n3rditorium.smartbedside.system.brightness.AdjustBrightnessPresenter;
import com.n3rditorium.smartbedside.system.wifi.WifiInfoPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = { AndroidModule.class, TimeModule.class, SystemModule.class })
public interface AppComponent {

   BaseApplication application();

   Context context();

   void inject(CurrentTimePresenter presenter);

   void inject(DebugInfoPresenter debugInfoPresenter);

   void inject(WifiInfoPresenter wifiInfoPresenter);

   void inject(AdjustBrightnessPresenter adjustBrightnessPresenter);

   void inject(SimpleLedPresenter simpleLedPresenter);

   void inject(SliderPresenter sliderPresenter);

   void inject(CameraPresenter cameraPresenter);
}
