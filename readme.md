# Readme

## Android Thing

### Setup for Windows

#### Prepare your SD-Card

tbd

#### Wifi Setup

Source: [Android Things/Raspberry Pi 3](https://developer.android.com/things/hardware/raspberrypi.html#connecting_wi-fi)

To connect your device to you Wifi execute this command
```
am startservice -n com.google.wifisetup/.WifiSetupService -a WifiSetupService.Connect -e ssid AlphaCentauri2G -e passphrase64 c0gxMjEwJiZmRjkwNEBkYS5oMGFtIzIzMTI=
```

**Good to know**
- If you are using a Raspberry 3 ensure you are connecting to 2G network
- If your passprase contains special character use Base64 encoding


To reset the Wifi configuration on your device execute this command:
```
am startservice -n com.google.wifisetup/.WifiSetupService -a WifiSetupService.Reset
```


## APIs

- Weather: https://openweathermap.org/city/2849483
- Sunrise and Sunset: https://www.programmableweb.com/api/sunrise-and-sunset-times
