package com.n3rditorium.smartbedside.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.n3rditorium.common.mvp.BaseView;
import com.n3rditorium.smartbedside.MainActivity;
import com.n3rditorium.smartbedside.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class CameraView extends BaseView<CameraContract.View, CameraPresenter>
      implements CameraContract.View {

   @BindView (R.id.image_view)
   ImageView imageView;
   private Subscription subscription;

   public CameraView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   @Override
   public void displayImage(Bitmap bitmap) {
      ((MainActivity) getContext()).displayImage(imageView, bitmap);
   }

   @Override
   protected CameraPresenter createPresenter() {
      return new CameraPresenter();
   }

   @Override
   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
   }

   @Override
   protected void onViewCreated() {
      ButterKnife.bind(this);
      super.onViewCreated();
   }

   @OnClick (R.id.init_camera)
   void initCamera() {
      getPresenter().init();
   }

   @OnClick (R.id.take_picture)
   void takePicture() {
      if (subscription == null) {
         subscription = Observable.interval(200, TimeUnit.MILLISECONDS)
               .subscribe(new Action1<Long>() {
                  @Override
                  public void call(Long aLong) {
                     getPresenter().takePicture();
                  }
               });
      } else {
         subscription.unsubscribe();
         subscription = null;
      }
      //getPresenter().takePicture();
   }
}