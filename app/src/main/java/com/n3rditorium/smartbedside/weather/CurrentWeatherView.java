package com.n3rditorium.smartbedside.weather;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.n3rditorium.common.mvp.BaseView;
import com.n3rditorium.smartbedside.R;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentWeatherView
      extends BaseView<CurrentWeatherContract.View, CurrentWeatherPresenter>
      implements CurrentWeatherContract.View {

   @BindView (R.id.current_condition)
   TextView currentConditionView;
   @BindView (R.id.current_temperature)
   TextView currentTemperatureView;
   @BindArray (R.array.weather_conditions)
   String[] weatherConditions;

   public CurrentWeatherView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   @Override
   public void showCurrentConditions(int[] conditionIndices) {
      String[] conditions = new String[conditionIndices.length];
      for (int i = 0; i < conditionIndices.length; ++i) {
         conditions[i] = weatherConditions[conditionIndices[i]];
      }
      String conditionText = TextUtils.join(", ", conditions);
      currentConditionView.setText(conditionText);
   }

   @Override
   public void showCurrentTemperatureInCelsius(float temperature) {
      String formattedTemperature = getString(R.string.current_temperature_celsius, temperature);
      currentTemperatureView.setText(formattedTemperature);
   }

   @Override
   protected CurrentWeatherPresenter createPresenter() {
      return new CurrentWeatherPresenter();
   }

   @Override
   protected void onViewCreated() {
      ButterKnife.bind(this);
      super.onViewCreated();
   }
}
