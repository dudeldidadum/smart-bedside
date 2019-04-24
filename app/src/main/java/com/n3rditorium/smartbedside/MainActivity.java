package com.n3rditorium.smartbedside;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.n3rditorium.common.utils.ExternalIntentUtils;
import com.n3rditorium.smartbedside.system.systemui.SystemBarService;

import java.util.List;

import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      ButterKnife.bind(this);

      checkWriteableSettings();
   }

   private void checkWriteableSettings() {
      if (!Settings.System.canWrite(this)) {
         Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
         intent.setData(Uri.parse("package:" + this.getPackageName()));
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         startActivity(intent);

      //queryInstalledApplications();
   }

   @Override
   protected void onResume() {
      super.onResume();
      new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {
            if (Settings.canDrawOverlays(MainActivity.this)) {
               startService(new Intent(MainActivity.this, SystemBarService.class));
            } else {
               ExternalIntentUtils.enableOverlayDrawing(MainActivity.this);
            }
         }
      }, 5000);

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
