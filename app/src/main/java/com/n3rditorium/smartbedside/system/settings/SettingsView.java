package com.n3rditorium.smartbedside.system.settings;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;

import com.n3rditorium.common.mvp.BaseView;
import com.n3rditorium.common.utils.ExternalIntentUtils;
import com.n3rditorium.smartbedside.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsView extends BaseView<SettingsContract.View, SettingsPresenter>
      implements SettingsContract.View {

   public SettingsView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   @Override
   protected SettingsPresenter createPresenter() {
      return new SettingsPresenter();
   }

   @Override
   protected void onViewCreated() {
      ButterKnife.bind(this);
      super.onViewCreated();
   }

   @OnClick (R.id.container)
   void onItemClicked() {
      ExternalIntentUtils.openSettings((Activity) getContext());
   }
}
