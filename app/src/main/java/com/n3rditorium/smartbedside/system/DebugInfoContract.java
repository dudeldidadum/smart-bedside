package com.n3rditorium.smartbedside.system;

import com.n3rditorium.smartbedside.core.BaseContract;

public interface DebugInfoContract {

   interface View extends BaseContract.View {

      void showDisplayInfo(String displayInfo);
   }
}
