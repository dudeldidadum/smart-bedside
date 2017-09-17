package com.intive.cultea.beta.core.module;

import android.content.Context;

import com.intive.cultea.beta.SplashScreen;
import com.intive.cultea.beta.TopLevelActivity;
import com.intive.cultea.beta.brewing.monitor.BrewingMonitorActivity;
import com.intive.cultea.beta.brewing.monitor.BrewingMonitorPresenter;
import com.intive.cultea.beta.brewing.parameter.BrewingParametersActivity;
import com.intive.cultea.beta.brewing.parameter.BrewingParametersPresenter;
import com.intive.cultea.beta.core.BaseApplication;
import com.intive.cultea.beta.device.mydevices.MyDevicesPresenter;
import com.intive.cultea.beta.device.status.DeviceStatusPresenter;
import com.intive.cultea.beta.home.HomeHeaderPresenter;
import com.intive.cultea.beta.more.MoreFragment;
import com.intive.cultea.beta.more.userprofile.ProfileHeaderPresenter;
import com.intive.cultea.beta.more.userprofile.UserProfileOverviewPresenter;
import com.intive.cultea.beta.more.userprofile.edit.UserProfileEditPresenter;
import com.intive.cultea.beta.qr.QRScannerActivity;
import com.intive.cultea.beta.statistics.StatisticsPresenter;
import com.intive.cultea.beta.statistics.items.StatisticsChartPresenter;
import com.intive.cultea.beta.statistics.items.charts.ColumnPopupAdapter;
import com.intive.cultea.beta.statistics.items.charts.ConsumptionLineChartPresenter;
import com.intive.cultea.beta.statistics.items.charts.CountryBarChartPresenter;
import com.intive.cultea.beta.statistics.items.charts.TeaBarChartPresenter;
import com.intive.cultea.beta.welcome.CredentialsInputPresenter;
import com.intive.cultea.beta.welcome.forgotpassword.ForgotPasswordPresenter;
import com.intive.cultea.beta.welcome.login.LoginPresenter;
import com.intive.cultea.beta.welcome.register.RegisterActivity;
import com.intive.cultea.beta.welcome.register.RegisterStepOnePresenter;
import com.intive.cultea.beta.welcome.register.RegisterStepThreePresenter;
import com.intive.cultea.beta.welcome.register.RegisterStepTwoPresenter;
import com.intive.cultea.beta.welcome.register.color.PickedColorPresenter;
import com.intive.cultea.beta.wifi.connection.WifiConnectionStatusPresenter;
import com.intive.cultea.beta.wifi.credentials.WifiCredentialsActivity;
import com.intive.cultea.beta.wifi.credentials.WifiCredentialsPresenter;
import com.intive.cultea.beta.wifi.devicename.WifiDeviceNamePresenter;
import com.intive.cultea.beta.wifi.hotspotinfo.WifiHotspotInfoPresenter;
import com.intive.cultea.framework.core.module.BusinessModule;
import com.intive.cultea.framework.core.module.DeviceModule;
import com.intive.cultea.framework.core.module.WebserviceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = { ApplicationModule.class, BusinessModule.class, WebserviceModule.class,
      DeviceModule.class, CulteaModule.class })
public interface AppComponent {

   BaseApplication application();

   Context context();

   void inject(SplashScreen splashScreen);

   void inject(CredentialsInputPresenter credentialsInputPresenter);

   void inject(RegisterStepOnePresenter registerStepOnePresenter);

   void inject(LoginPresenter loginPresenter);

   void inject(RegisterStepTwoPresenter registerStepTwoPresenter);

   void inject(ForgotPasswordPresenter forgotPasswordPresenter);

   void inject(UserProfileOverviewPresenter userProfileOverviewPresenter);

   void inject(MoreFragment moreFragment);

   void inject(UserProfileEditPresenter userProfileEditPresenter);

   void inject(WifiCredentialsPresenter wifiCredentialsPresenter);

   void inject(WifiHotspotInfoPresenter wifiHotspotInfoPresenter);

   void inject(WifiConnectionStatusPresenter wifiConnectionStatusPresenter);

   void inject(WifiCredentialsActivity wifiCredentialsActivity);

   void inject(TopLevelActivity topLevelActivity);

   void inject(QRScannerActivity qrScannerActivity);

   void inject(DeviceStatusPresenter deviceStatusPresenter);

   void inject(RegisterStepThreePresenter registerStepThreePresenter);

   void inject(PickedColorPresenter pickedColorPresenter);

   void inject(RegisterActivity registerActivity);

   void inject(MyDevicesPresenter myDevicesPresenter);

   void inject(BrewingMonitorPresenter brewingMonitorPresenter);

   void inject(BrewingParametersPresenter brewingParametersPresenter);

   void inject(WifiDeviceNamePresenter wifiDeviceNamePresenter);

   void inject(StatisticsChartPresenter statisticsChartPresenter);

   void inject(HomeHeaderPresenter homeHeaderPresenter);

   void inject(ConsumptionLineChartPresenter consumptionLineChartPresenter);

   void inject(CountryBarChartPresenter countryBarChartPresenter);

   void inject(TeaBarChartPresenter teaBarChartPresenter);

   void inject(BrewingParametersActivity brewingParametersActivity);

   void inject(BrewingMonitorActivity brewingMonitorActivity);

   void inject(ColumnPopupAdapter columnPopupAdapter);

   void inject(StatisticsPresenter statisticsPresenter);

   void inject(ProfileHeaderPresenter profileHeaderPresenter);
}
