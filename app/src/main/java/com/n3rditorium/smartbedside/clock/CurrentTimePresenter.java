package com.n3rditorium.smartbedside.clock;

import com.n3rditorium.common.mvp.BasePresenter;
import com.n3rditorium.core.time.DateFormatter;
import com.n3rditorium.core.time.TimeFormatter;
import com.n3rditorium.smartbedside.injection.Injector;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.TimeInterval;
import timber.log.Timber;

public class CurrentTimePresenter extends BasePresenter<CurrentTimeContract.View> {

   @Inject
   DateFormatter dateFormatter;
   @Inject
   TimeFormatter timeFormatter;

   @Override
   protected void bindView(CurrentTimeContract.View view) {
      super.bindView(view);
      Injector.getAppComponent()
            .inject(this);

      handleFomrattedTime(timeFormatter.formatNow());
      createTimeObservable().subscribe(new Action1<String>() {
         @Override
         public void call(String s) {
            handleFomrattedTime(s);
         }
      }, new Action1<Throwable>() {
         @Override
         public void call(Throwable throwable) {
            Timber.e(throwable);
         }
      });

      handleFormattedDate(dateFormatter.formatNow());
      createDateObservable().subscribe(new Action1<String>() {
         @Override
         public void call(String s) {
            handleFormattedDate(s);
         }
      }, new Action1<Throwable>() {
         @Override
         public void call(Throwable throwable) {
            Timber.e(throwable);
         }
      });
   }

   private Observable<String> createDateObservable() {
      return Observable.interval(1, TimeUnit.MINUTES)
            .timeInterval()
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(

                  new Func1<TimeInterval<Long>, Observable<String>>() {
                     @Override
                     public Observable<String> call(TimeInterval<Long> longTimeInterval) {
                        return Observable.just(dateFormatter.formatNow());
                     }
                  });
   }

   private Observable<String> createTimeObservable() {
      return Observable.interval(1, TimeUnit.SECONDS)
            .timeInterval()
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(

                  new Func1<TimeInterval<Long>, Observable<String>>() {
                     @Override
                     public Observable<String> call(TimeInterval<Long> longTimeInterval) {
                        return Observable.just(timeFormatter.formatNow());
                     }
                  });
   }

   private void handleFomrattedTime(String s) {
      getView().displayCurrentTime(s);
   }

   private void handleFormattedDate(String s) {
      String[] array = s.split("#");
      getView().displayCurrentDate(array[0], array[1].toUpperCase(Locale.getDefault()));
   }
}
