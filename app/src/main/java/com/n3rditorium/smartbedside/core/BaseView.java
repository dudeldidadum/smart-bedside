package com.n3rditorium.smartbedside.core;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public abstract class BaseView<V extends BaseContract.View, P extends BasePresenter<V>>
      extends FrameLayout implements BaseContract.View {

   private boolean inflated;
   private P presenter;

   public BaseView(Context context) {
      super(context);
      initialize(context, null, 0);
   }

   public BaseView(Context context, AttributeSet attrs) {
      super(context, attrs);
      initialize(context, attrs, 0);
   }

   public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      initialize(context, attrs, defStyleAttr);
   }

   public P getPresenter() {
      return presenter;
   }

   @Override
   public String getString(@StringRes int resId) {
      if (getContext() == null) {
         return "";
      }
      return getContext().getString(resId);
   }

   protected abstract P createPresenter();

   @CallSuper
   protected void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
   }

   @Override
   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      if (!inflated) {
         onViewCreated();
      }
      bindPresenter();
   }

   @Override
   protected void onDetachedFromWindow() {
      unbindPresenter();
      super.onDetachedFromWindow();
   }

   @Override
   @CallSuper
   protected void onFinishInflate() {
      super.onFinishInflate();
      inflated = true;
      onViewCreated();
      bindPresenter();
   }

   @Override
   protected void onRestoreInstanceState(Parcelable state) {
      bindPresenter();
      if (state instanceof Bundle) {
         Bundle bundle = (Bundle) state;
         super.onRestoreInstanceState(bundle.getParcelable(super.getClass()
               .getSimpleName()));
      } else {
         super.onRestoreInstanceState(state);
      }
   }

   @Override
   protected Parcelable onSaveInstanceState() {
      Bundle bundle = new Bundle();
      bundle.putParcelable(this.getClass()
            .getSimpleName(), super.onSaveInstanceState());
      return bundle;
   }

   @CallSuper
   protected void onViewCreated() {
   }

   @SuppressWarnings ("unchecked")
   private void bindPresenter() {
      if (presenter == null) {
         presenter = createPresenter();
         presenter.bindView((V) this);
      }
   }

   private void unbindPresenter() {
      if (presenter != null) {
         presenter.unbindView();
         presenter = null;
      }
   }
}
