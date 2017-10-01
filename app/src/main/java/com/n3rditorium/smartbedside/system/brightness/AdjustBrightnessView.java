package com.n3rditorium.smartbedside.system.brightness;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.n3rditorium.common.mvp.BaseView;

import butterknife.ButterKnife;
import timber.log.Timber;

public class AdjustBrightnessView
      extends BaseView<AdjustBrightnessContract.View, AdjustBrightnessPresenter>
      implements AdjustBrightnessContract.View {

   public AdjustBrightnessView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   @Override
   public void displayCurrentBrightness(int brightness) {
      Timber.d("current brightness &d", brightness);
      // TODO
   }

   @Override
   public void hideAdjustBrightnessView() {
      setVisibility(View.GONE);
   }

   @Override
   protected AdjustBrightnessPresenter createPresenter() {
      return new AdjustBrightnessPresenter();
   }

   @Override
   protected void onViewCreated() {
      ButterKnife.bind(this);
      super.onViewCreated();
   }
}
