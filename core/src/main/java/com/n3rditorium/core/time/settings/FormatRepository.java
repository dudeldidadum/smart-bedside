package com.n3rditorium.core.time.settings;

import android.support.v4.util.Pair;

import com.n3rditorium.smartbedside.core.R;

public class FormatRepository {

   static final Pair<Integer, String> DATE_DE = new Pair<>(1, "d. MMMM#EEEE, yyyy");
   static final Pair<Integer, String> TIME_12 = new Pair<>(R.string.time_format_12, "K:mm a");
   static final Pair<Integer, String> TIME_24 = new Pair<>(R.string.time_format_24, "H:mm");


   public static final String TIMEZONE_BERLIN = "Europe/Berlin";
   private Pair<Integer, String> currentDateFormat = DATE_DE;
   private Pair<Integer, String> currentTimeFormat = TIME_24;

   public Pair<Integer, String> getCurrentDateFormat() {
      return currentDateFormat;
   }

   public Pair<Integer, String> getCurrentTimeFormat() {
      return currentTimeFormat;
   }

   public void use12Hours() {
      this.currentTimeFormat = TIME_12;
   }

   public void use24Hours() {
      this.currentTimeFormat = TIME_24;
   }
}
