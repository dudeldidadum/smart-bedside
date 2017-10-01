package com.n3rditorium.smartbedside.system.wifi;

<<<<<<< Updated upstream
=======
import android.app.Activity;
>>>>>>> Stashed changes
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

<<<<<<< Updated upstream
import com.n3rditorium.smartbedside.R;
import com.n3rditorium.smartbedside.core.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
=======
import com.n3rditorium.common.mvp.BaseView;
import com.n3rditorium.common.utils.ExternalIntentUtils;
import com.n3rditorium.smartbedside.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
>>>>>>> Stashed changes

public class WifiInfoView extends BaseView<WifiInfoContract.View, WifiInfoPresenter>
      implements WifiInfoContract.View {

   @BindView (R.id.signal)
   View signalView;
   @BindView (R.id.ssid)
   TextView ssidView;

   public WifiInfoView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   @Override
   public void displaySSID(String ssid) {
      ssidView.setText(ssid);
   }

   @Override
   public void displaySignalStrength(int signalStrength) {
<<<<<<< Updated upstream
      float scale = 0.25f *signalStrength;
      if (scale > 1.f){
=======
      float scale = 0.25f * signalStrength;
      if (scale > 1.f) {
>>>>>>> Stashed changes
         scale = 1.f;
      }
      signalView.setScaleX(scale);
      signalView.setScaleY(scale);
   }

   @Override
   protected WifiInfoPresenter createPresenter() {
      return new WifiInfoPresenter();
   }

   @Override
   protected void onViewCreated() {
      ButterKnife.bind(this);
      super.onViewCreated();
   }
<<<<<<< Updated upstream
=======

   @OnClick (R.id.container)
   void onItemClicked() {
      ExternalIntentUtils.openWifiSettings((Activity) getContext());
   }
>>>>>>> Stashed changes
}
