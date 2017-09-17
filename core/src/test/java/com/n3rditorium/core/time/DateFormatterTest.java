package com.n3rditorium.core.time;

import com.n3rditorium.core.time.settings.FormatRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DateFormatterTest {

   private long UNIX_LOCAL = 481894631000L;
   private long UNIX_UTC = 481901831000L;
   private Calendar calendar;
   private FormatRepository formatRepository;
   private DateFormatter formatter;

   @Test
   public void formatInvalid() throws Exception {
      try {
         formatter.formatDate(null);
         fail();
      } catch (IllegalArgumentException e) {
         //everything ok
      }

      for (String tz : TimeZone.getAvailableIDs()) {
         System.out.println(tz);
      }
   }

   @Test
   public void formatDateDE() throws Exception {
      calendar.setTimeInMillis(UNIX_LOCAL);
      assertEquals("9. April#Dienstag, 1985", formatter.formatDate(calendar));

      calendar.setTimeInMillis(UNIX_UTC);
      assertEquals("9. April#Dienstag, 1985", formatter.formatDate(calendar));
   }



   @Before
   public void init() {
      formatRepository = new FormatRepository();
      formatter = new DateFormatter(formatRepository);
      calendar = Calendar.getInstance();
   }
}