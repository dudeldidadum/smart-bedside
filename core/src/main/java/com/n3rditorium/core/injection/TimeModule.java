package com.n3rditorium.core.time.injection;

import com.n3rditorium.core.time.TimeFormatter;
import com.n3rditorium.core.time.settings.FormatRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TimeModule {

   @Provides
   @Singleton
   FormatRepository providesFormatRepository() {
      return new FormatRepository();
   }

   @Provides
   TimeFormatter providesTimeFormatter(FormatRepository formatRepository) {
      return new TimeFormatter(formatRepository);
   }
}
