package com.n3rditorium.smartbedside.weather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.snapshot.WeatherResponse;
import com.google.android.gms.awareness.state.Weather;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.n3rditorium.common.mvp.BasePresenter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.TimeInterval;
import timber.log.Timber;

public class CurrentWeatherPresenter extends BasePresenter<CurrentWeatherContract.View> {

   private Subscription weatherSubscription;

   @Override
   protected void bindView(CurrentWeatherContract.View view) {
      super.bindView(view);
      startWeatherObservation();
   }

   @Override
   protected void unbindView() {
      super.unbindView();
      if (weatherSubscription != null && !weatherSubscription.isUnsubscribed()) {
         weatherSubscription.unsubscribe();
      }
   }

   private Observable<TimeInterval<Long>> createTickObservable() {
      return Observable.interval(1, TimeUnit.MINUTES)
            .timeInterval()
            .observeOn(AndroidSchedulers.mainThread());
   }

   // Permission check is done in calling method
   @SuppressLint ("MissingPermission")
   private void loadAndShowCurrentWeather() {
      Awareness.getSnapshotClient(getView().getContext())
            .getWeather()
            .addOnSuccessListener(new OnSuccessListener<WeatherResponse>() {
               @Override
               public void onSuccess(WeatherResponse weatherResponse) {
                  Weather weather = weatherResponse.getWeather();

                  getView().showCurrentTemperatureInCelsius(
                        weather.getTemperature(Weather.CELSIUS));
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

   private void startWeatherObservation() {
      if (ActivityCompat.checkSelfPermission(getView().getContext(),
            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
         // TODO: Consider calling  ActivityCompat#requestPermissions
         return;
      }
      loadAndShowCurrentWeather();
      weatherSubscription = createTickObservable().subscribe(new Action1<TimeInterval<Long>>() {
         @Override
         public void call(TimeInterval<Long> longTimeInterval) {
            Timber.d("tick - loadAndDisplayCurrentWeather " + longTimeInterval.getIntervalInMilliseconds());
            loadAndShowCurrentWeather();
         }
      }, new Action1<Throwable>() {
         @Override
         public void call(Throwable throwable) {
            Timber.e(throwable);
         }
      });
   }
}
