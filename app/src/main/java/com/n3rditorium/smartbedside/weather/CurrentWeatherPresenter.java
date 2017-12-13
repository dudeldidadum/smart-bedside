package com.n3rditorium.smartbedside.weather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.snapshot.WeatherResponse;
import com.google.android.gms.awareness.state.Weather;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.n3rditorium.common.mvp.BasePresenter;
import com.n3rditorium.smartbedside.R;

import timber.log.Timber;

public class CurrentWeatherPresenter extends BasePresenter<CurrentWeatherContract.View> {

   @Override
   protected void bindView(CurrentWeatherContract.View view) {
      super.bindView(view);
      loadAndShowCurrentWeather();
   }

   private void loadAndShowCurrentWeather() {
      if (ActivityCompat.checkSelfPermission(getView().getContext(),
            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
         // TODO: Consider calling
         //    ActivityCompat#requestPermissions
         // here to request the missing permissions, and then overriding
         //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
         //                                          int[] grantResults)
         // to handle the case where the user grants the permission. See the documentation
         // for ActivityCompat#requestPermissions for more details.
         return;
      }
      Awareness.getSnapshotClient(getView().getContext())
            .getWeather()
            .addOnSuccessListener(new OnSuccessListener<WeatherResponse>() {
               @Override
               public void onSuccess(WeatherResponse weatherResponse) {
                  Weather weather = weatherResponse.getWeather();
                  Timber.d("weather: %s", weather);
                  String temperature = getView().getString(R.string.current_temperature_celsius,
                        weather.getTemperature(Weather.CELSIUS));
                  getView().showCurrentTemperature(temperature);
                  getView().showCurrentConditions(weather.getConditions());
               }
            })
            .addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                  Timber.e(e, "could not get weather");
               }
            });
   }
}
