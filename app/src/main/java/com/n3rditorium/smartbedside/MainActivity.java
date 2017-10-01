package com.n3rditorium.smartbedside;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import java.util.List;

import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      ButterKnife.bind(this);
   }

   @Override
   protected void onResume() {
      super.onResume();
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
         Timber.i("#%d - %s (%s)", i++, label, info.packageName);
         if (TextUtils.equals("com.android.settings", info.packageName)) {
            Intent launchApp = getPackageManager().getLaunchIntentForPackage(info.packageName);
            startActivity(launchApp);
         }
      }
   }
}
