package com.n3rditorium.core.time;

import com.n3rditorium.core.time.settings.FormatRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateFormatter {

   private final FormatRepository formatRepository;
   private SimpleDateFormat dateFormat;

   public DateFormatter(FormatRepository formatRepository) {
      this.formatRepository = formatRepository;
   }

   public String formatNow() {
      return formatDate(Calendar.getInstance());
   }

   String formatDate(Calendar calendar) {
      if (calendar == null) {
         throw new IllegalArgumentException("calendar must not be null");
      }
      sanitize();
      return dateFormat.format(calendar.getTime());
   }

   private void sanitize() {
      if (dateFormat == null) {
         dateFormat =
               new SimpleDateFormat(formatRepository.getCurrentDateFormat().second, Locale.GERMANY);
      }
   }
}
