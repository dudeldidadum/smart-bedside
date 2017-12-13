package com.n3rditorium.smartbedside;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;

import com.n3rditorium.common.utils.ExternalIntentUtils;

import java.util.List;

import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

   @Override
   public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
         @NonNull int[] grantResults) {
      switch (requestCode) {
         case ExternalIntentUtils.PERMISSION_REQUEST_FINE_LOCATION: {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               // requestWeather();
               // requestPlaces();
            } else {
               Timber.e("permission denied, boo!");
               // permission denied, boo! Disable the
               // functionality that depends on this permission.
            }
            return;
         }
         default:
            Timber.d("other permission results should be handles here");
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
         //requestWeather();
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
      }
   }

   /**
    @SuppressLint ("MissingPermission")
    private void requestPlaces() {
    Awareness.getSnapshotClient(this)
    .getPlaces()
    .addOnSuccessListener(new OnSuccessListener<PlacesResponse>() {
    @Override public void onSuccess(PlacesResponse placesResponse) {
    List<PlaceLikelihood> places = placesResponse.getPlaceLikelihoods();
    if (places == null || places.isEmpty()) {
    Timber.e("response has no places");
    return;
    }
    for (PlaceLikelihood place : places) {
    Timber.d("Place: %s", place.getPlace()
    .getName());
    }
    }
    })
    .addOnFailureListener(new OnFailureListener() {
    @Override public void onFailure(@NonNull Exception e) {
    Timber.e(e, "could not get places");
    }
    });
    }**/

}
