package com.n3rditorium.smartbedside.core;

import android.content.Context;
import android.support.annotation.StringRes;

public interface BaseContract {

   interface View {

      Context getContext();

      String getString(@StringRes int resId);
   }
}
