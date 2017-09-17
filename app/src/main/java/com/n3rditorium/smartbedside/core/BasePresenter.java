package com.n3rditorium.smartbedside.core;

import android.support.annotation.CallSuper;

public abstract class BasePresenter<V> {
   private V view;

   protected V getView() {
      return view;
   }


   @CallSuper
   protected void bindView(V view) {
      this.view = view;
   }

   @CallSuper
   protected void unbindView() {
      view = null;
   }
}
