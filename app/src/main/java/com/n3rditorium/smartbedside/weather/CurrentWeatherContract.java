package com.n3rditorium.smartbedside.weather;

import com.n3rditorium.common.mvp.BaseContract;

public interface CurrentWeatherContract {

   interface View extends BaseContract.View {

      void showCurrentConditions(int[] conditionIndex);

      void showCurrentTemperatureInCelsius(float currentTemperature);
   }
}
