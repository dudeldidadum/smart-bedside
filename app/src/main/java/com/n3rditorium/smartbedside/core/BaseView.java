package com.intive.cultea.beta.core;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public abstract class BaseView<V, P extends BasePresenter<V>> extends FrameLayout {

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

   protected abstract P createPresenter();

   @CallSuper
   protected void inflatedViews() {
   }

   @CallSuper
   protected void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
   }

   @Override
   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      bindPresenter();
      if (!inflated) {
         inflatedViews();
      }
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
      bindPresenter();
      inflatedViews();
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
