package com.n3rditorium.smartbedside.clock;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.n3rditorium.smartbedside.R;
import com.n3rditorium.smartbedside.core.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentTimeView extends BaseView<CurrentTimeContract.View, CurrentTimePresenter>
      implements CurrentTimeContract.View {

   @BindView (R.id.current_date_1)
   TextView currentDate1;
   @BindView (R.id.current_date_2)
   TextView currentDate2;
   @BindView (R.id.current_time)
   TextView currentTime;

   public CurrentTimeView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   @Override
   public void displayCurrentDate(String line1, String line2) {
      currentDate1.setText(line1);
      currentDate2.setText(line2);
   }

   @Override
   public void displayCurrentTime(String text) {
      currentTime.setText(text);
   }

   @Override
   protected CurrentTimePresenter createPresenter() {
      return new CurrentTimePresenter();
   }

   @Override
   protected void onViewCreated() {
      ButterKnife.bind(this);
      super.onViewCreated();
   }
}
