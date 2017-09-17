package com.n3rditorium.smartbedside.injection;

import android.content.Context;

import com.n3rditorium.core.injection.TimeModule;
import com.n3rditorium.smartbedside.clock.CurrentTimePresenter;
import com.n3rditorium.smartbedside.core.BaseApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = { AndroidModule.class, TimeModule.class })
public interface AppComponent {

   BaseApplication application();

   Context context();

   void inject(CurrentTimePresenter presenter);
}
