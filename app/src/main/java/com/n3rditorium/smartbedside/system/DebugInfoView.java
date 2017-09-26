package com.n3rditorium.smartbedside.system;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.n3rditorium.smartbedside.R;
import com.n3rditorium.smartbedside.core.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class DebugInfoView extends BaseView<DebugInfoContract.View, DebugInfoPresenter>
      implements DebugInfoContract.View {

   @BindView (R.id.display_info)
   TextView displayInfoView;

   @BindView (R.id.network_info)
   TextView networkInfoView;

   public DebugInfoView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   @Override
   public void showDisplayInfo(String displayInfo) {
      displayInfoView.setText(displayInfo);
   }

   @Override
   public void showNetworkInfo(String networkInfo) {
      networkInfoView.setText(networkInfo);
   }

   @Override
   protected DebugInfoPresenter createPresenter() {
      return new DebugInfoPresenter();
   }

   @Override
   protected void onViewCreated() {
      ButterKnife.bind(this);
      super.onViewCreated();
   }

   @OnClick (R.id.display_info)
   void onClick() {
      Timber.d("NICE! Clicking my fancy view is working");
   }
}
