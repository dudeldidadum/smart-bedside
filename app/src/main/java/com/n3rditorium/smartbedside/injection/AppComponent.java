package com.n3rditorium.smartbedside.injection;

import android.content.Context;

import com.n3rditorium.core.injection.SystemModule;
import com.n3rditorium.core.injection.TimeModule;
import com.n3rditorium.smartbedside.clock.CurrentTimePresenter;
import com.n3rditorium.smartbedside.core.BaseApplication;
import com.n3rditorium.smartbedside.system.DebugInfoPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = { AndroidModule.class, TimeModule.class, SystemModule.class })
public interface AppComponent {

   BaseApplication application();

   Context context();

   void inject(CurrentTimePresenter presenter);

   void inject(DebugInfoPresenter debugInfoPresenter);
}
