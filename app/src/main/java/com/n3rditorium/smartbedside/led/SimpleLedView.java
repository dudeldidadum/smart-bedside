package com.n3rditorium.smartbedside.led;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;
import com.n3rditorium.common.mvp.BaseView;
import com.n3rditorium.smartbedside.BoardDefaults;
import com.n3rditorium.smartbedside.R;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

public class SimpleLedView extends BaseView<SimpleLedContract.View, SimpleLedPresenter>
      implements SimpleLedContract.View {

   private Gpio mLedGpio;
   private boolean mLedState = false;

   public SimpleLedView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   @Override
   protected SimpleLedPresenter createPresenter() {
      return new SimpleLedPresenter();
   }

   @Override
   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      // Remove pending blink Runnable from the handler.
      // Close the Gpio pin.
      Log.i(TAG, "Closing LED GPIO pin");
      try {
         mLedGpio.close();
      } catch (IOException e) {
         Log.e(TAG, "Error on PeripheralIO API", e);
      } finally {
         mLedGpio = null;
      }
   }

   @Override
   protected void onViewCreated() {
      ButterKnife.bind(this);
      super.onViewCreated();

      PeripheralManagerService service = new PeripheralManagerService();
      try {
         String pinName = BoardDefaults.getGPIOForLED();
         mLedGpio = service.openGpio(pinName);
         mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
      } catch (IOException e) {
         Log.e(TAG, "Error on PeripheralIO API", e);
      }

      List<String> portList = service.getPwmList();
      if (portList.isEmpty()) {
         Log.i(TAG, "No PWM port available on this device.");
      } else {
         Log.i(TAG, "List of available ports: " + portList);
      }
   }

   @OnClick (R.id.container)
   void onLedToggle() {
      if (mLedGpio == null) {
         return;
      }
      // Toggle the GPIO state
      mLedState = !mLedState;
      try {
         mLedGpio.setValue(mLedState);
         Log.d(TAG, "State set to " + mLedState);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
