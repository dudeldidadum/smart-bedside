package com.n3rditorium.smartbedside;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.snapshot.PlacesResult;
import com.google.android.gms.awareness.snapshot.WeatherResult;
import com.google.android.gms.awareness.state.Weather;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.n3rditorium.common.utils.ExternalIntentUtils;

import java.util.List;

import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

   private GoogleApiClient client;
   private PendingIntent pendingIntent;

   @Override
   public void onRequestPermissionsResult(int requestCode, String permissions[],
         int[] grantResults) {
      switch (requestCode) {
         case ExternalIntentUtils.PERMISSION_REQUEST_FINE_LOCATION: {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               requestWeather();
            } else {
               // permission denied, boo! Disable the
               // functionality that depends on this permission.
            }
            return;
         }

         // other 'case' lines to check for other
         // permissions this app might request
      }
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      ButterKnife.bind(this);

      queryInstalledApplications();
      if (!isFineLocationGranted()) {
         ExternalIntentUtils.requestFineLocationPermission(this);
      } else {
         requestWeather();
      }
   }

   @Override
   protected void onResume() {
      super.onResume();
   }

   @PermissionChecker.PermissionResult
   private boolean isFineLocationGranted() {
      return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED;
   }

   private void queryInstalledApplications() {
      Timber.d("query using Intent");
      Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
      mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
      List<ResolveInfo> infoList = getPackageManager().queryIntentActivities(mainIntent, 0);

      int i = 0;
      for (ResolveInfo info : infoList) {
         CharSequence label = info.loadLabel(getPackageManager());
         Timber.i("#%d - %s (%s)", i++, label, info.resolvePackageName);
      }
      Timber.d("query using PackageManager");
      List<PackageInfo> apps = getPackageManager().getInstalledPackages(0);
      i = 0;
      for (PackageInfo info : apps) {
         CharSequence label = info.applicationInfo.loadLabel(getPackageManager());
         Timber.i("#%d - %s (%s)%s", i++, label, info.packageName, info.versionName);
         if (TextUtils.equals("com.android.settings", info.packageName)) {
            Intent launchApp = getPackageManager().getLaunchIntentForPackage(info.packageName);
            //    startActivity(launchApp);
         }
      }
   }

   @SuppressWarnings ("MissingPermission")
   private void requestWeather() {
      client = new GoogleApiClient.Builder(this).addApi(Awareness.API)
            .enableAutoManage(this, 1, null)
            .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
               @Override
               public void onConnected(@Nullable Bundle bundle) {
                  Timber.d("GoogleApiClient connected");
               }

               @Override
               public void onConnectionSuspended(int i) {
                  Timber.e("GoogleApiClient connection suspended");
               }
            })
            .build();

      Awareness.SnapshotApi.getPlaces(client)
            .setResultCallback(new ResultCallback<PlacesResult>() {
               @Override
               public void onResult(@NonNull PlacesResult placesResult) {
                  Timber.d("Places result received: " + placesResult.getStatus());
                  Timber.d("Places result code" + placesResult.getStatus()
                        .getStatusCode());
                  Timber.d("Places result message " + placesResult.getStatus()
                        .getStatusMessage());
                  if (placesResult.getStatus()
                        .isSuccess() && placesResult.getPlaceLikelihoods() != null) {
                     List<PlaceLikelihood> places = placesResult.getPlaceLikelihoods();
                     for (PlaceLikelihood place : places) {
                        Timber.d("Place: " + place.getPlace());
                     }
                  }
               }
            });

      Awareness.SnapshotApi.getWeather(client)
            .setResultCallback(new ResultCallback<WeatherResult>() {
               @Override
               public void onResult(@NonNull WeatherResult weatherResult) {
                  Timber.d("Weather result received: " + weatherResult.getStatus());
                  Timber.d("Weather result code" + weatherResult.getStatus()
                        .getStatusCode());
                  Timber.d("Weather result message " + weatherResult.getStatus()
                        .getStatusMessage());
                  if (weatherResult.getStatus()
                        .isSuccess()) {
                     Weather weather = weatherResult.getWeather();
                     Timber.d("weather: " + weather);
                     int[] conditions = weather.getConditions();
                  }
               }
            });
   }
}
