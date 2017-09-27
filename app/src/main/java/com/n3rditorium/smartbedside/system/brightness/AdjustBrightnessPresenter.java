package com.n3rditorium.smartbedside.system.brightness;

import android.content.ContentResolver;
import android.provider.Settings;

import com.n3rditorium.smartbedside.core.BasePresenter;
import com.n3rditorium.smartbedside.injection.Injector;

import javax.inject.Inject;

import timber.log.Timber;

public class AdjustBrightnessPresenter extends BasePresenter<AdjustBrightnessContract.View> {

   @Inject
   ContentResolver contentResolver;

   @Override
   protected void bindView(AdjustBrightnessContract.View view) {
      super.bindView(view);
      Injector.getAppComponent()
            .inject(this);
      loadAndShowBrightness();
   }

   @SuppressWarnings ("unused")
   void saveBrightness(final int brightness) {
      //Set the system brightness using the brightness variable value
      Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
      /*
       //Get the current window attributes
       WindowManager.LayoutParams params = window.getAttributes();
       //Set the brightness of this window
       params.screenBrightness = (float) newBrightness / 255f;
       //Apply attribute changes to this window
       window.setAttributes(params);
       getWindow().addFlags(WindowManager.LayoutParams.FLAGS_CHANGED);
       */

   }

   private void loadAndShowBrightness() {
      int brightness = -1;
      try {
         brightness = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS);
      } catch (Settings.SettingNotFoundException e) {
         Timber.e(e);
      }
      if (brightness < 0) {
         getView().hideAdjustBrightnessView();
      } else {
         getView().displayCurrentBrightness(brightness);
      }
   }
}
