package com.n3rditorium.smartbedside;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
   }

   @Override
   public boolean onKeyDown(int keyCode, KeyEvent event) {
      Timber.d("wuuhuuu!! Touch is working");
      return super.onKeyDown(keyCode, event);
   }
}
