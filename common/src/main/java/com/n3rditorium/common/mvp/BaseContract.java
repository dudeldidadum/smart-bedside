package com.n3rditorium.common.mvp;

import android.content.Context;
import android.support.annotation.StringRes;

public interface BaseContract {

   interface View {

      Context getContext();

      String getString(@StringRes int resId);

      String getString(@StringRes int resId, Object... args);
   }
}
