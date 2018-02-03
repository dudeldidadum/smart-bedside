package com.n3rditorium.smartbedside.led.brightness;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import com.google.android.things.pio.PeripheralManagerService;
import com.google.android.things.pio.Pwm;
import com.n3rditorium.common.mvp.BaseView;
import com.n3rditorium.smartbedside.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class SliderView extends BaseView<SliderContract.View, SliderPresenter>
      implements SliderContract.View {

   private static final String PWM_NAME = "PWM1";
   @BindView (R.id.seekbar)
   SeekBar seekbar;
   private PeripheralManagerService peripheralManagerService;
   private Pwm pwm;

   public SliderView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   @Override
   protected SliderPresenter createPresenter() {
      return new SliderPresenter();
   }

   @Override
   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
   }

   @Override
   protected void onViewCreated() {
      ButterKnife.bind(this);
      super.onViewCreated();
      peripheralManagerService = new PeripheralManagerService();
      prepareLedControl();
   }

   private void prepareLedControl() {
      try {
         pwm = peripheralManagerService.openPwm(PWM_NAME);
      } catch (IOException | NullPointerException e) {
         Timber.w(e, "Unable to access PWM");
      }

      try {
         pwm.setPwmFrequencyHz(240);
         pwm.setPwmDutyCycle(100);
         // Enable the PWM signal
         pwm.setEnabled(true);
      } catch (IOException e) {
         Timber.e(e, "unable to set Frequency");
      }
      seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            try {
               pwm.setPwmDutyCycle(progress);
            } catch (IOException e) {
               Timber.e(e, "unable to set duty cycle");
            }
         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {
            // not needed
         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {
            // not needed
         }
      });
   }
}
