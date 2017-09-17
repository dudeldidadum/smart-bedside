package com.n3rditorium.core.time;

import com.n3rditorium.core.time.settings.FormatRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TimeFormatterTest {

   private long UNIX_LOCAL = 481894631000L;
   private long UNIX_UTC = 481901831000L;
   private Calendar calendar;
   private FormatRepository formatRepository;
   private TimeFormatter formatter;

   @Test
   public void formatInvalid() throws Exception {
      try {
         formatter.formatTime(null);
         fail();
      } catch (IllegalArgumentException e) {
         //everything ok
      }
   }

   @Test
   public void formatTime12Local() throws Exception {
      calendar.setTimeInMillis(UNIX_LOCAL);
      formatRepository.use12Hours();
      assertEquals("01:37 PM", formatter.formatTime(calendar));
   }

   @Test
   public void formatTime12UTC() throws Exception {
      calendar.setTimeInMillis(UNIX_UTC);
      formatRepository.use12Hours();
      assertEquals("03:37 PM", formatter.formatTime(calendar));
   }

   @Test
   public void formatTime24Local() throws Exception {
      calendar.setTimeInMillis(UNIX_LOCAL);
      formatRepository.use24Hours();
      assertEquals("13:37", formatter.formatTime(calendar));
   }

   @Test
   public void formatTime24UTC() throws Exception {
      calendar.setTimeInMillis(UNIX_UTC);
      formatRepository.use24Hours();
      assertEquals("15:37", formatter.formatTime(calendar));
   }

   @Before
   public void init() {
      formatRepository = new FormatRepository();
      formatter = new TimeFormatter(formatRepository);
      calendar = Calendar.getInstance();
   }
}