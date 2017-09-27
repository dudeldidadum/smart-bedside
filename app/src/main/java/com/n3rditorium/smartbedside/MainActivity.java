package com.n3rditorium.smartbedside;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

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
      }
   }
}
