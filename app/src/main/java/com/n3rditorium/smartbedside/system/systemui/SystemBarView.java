package com.n3rditorium.smartbedside.system.systemui;

import android.content.Context;
import android.util.AttributeSet;

import com.n3rditorium.common.mvp.BaseView;

public class SystemBarView extends BaseView<SystemBarContract.View, SystemBarPresenter> {

   public SystemBarView(Context context) {
      super(context);
   }

   public SystemBarView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   public SystemBarView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   @Override
   protected SystemBarPresenter createPresenter() {
      return new SystemBarPresenter();
   }
}
