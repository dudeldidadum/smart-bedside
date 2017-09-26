package com.n3rditorium.smartbedside.system.wifi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.n3rditorium.smartbedside.R;
import com.n3rditorium.smartbedside.core.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;

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
      float scale;
      switch (signalStrength) {
         case 0:
            scale = 0.0f;
            break;
         case 1:
            scale = 0.2f;
            break;
         case 2:
            scale = 0.4f;
            break;
         case 3:
            scale = 0.6f;
            break;
         case 4:
            scale = 0.8f;
            break;
         case 5:
         default:
            scale = 1.0f;
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
}
