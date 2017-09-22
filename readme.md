# Readme

## Building a smart device with Android Things


### Setup (for Windows)

#### Prepare your SD-Card

** *in progress* **

### Connect your Pi to your Wifi

Source: [Android Things/Raspberry Pi 3](https://developer.android.com/things/hardware/raspberrypi.html#connecting_wi-fi)

To connect your device to you Wifi execute this command
```
am startservice -n com.google.wifisetup/.WifiSetupService -a WifiSetupService.Connect -e ssid AlphaCentauri2G -e passphrase64 c0gxMjEwJiZmRjkwNEBkYS5oMGFtIzIzMTI=
```

To reset the Wifi configuration on your device execute this command:
```
am startservice -n com.google.wifisetup/.WifiSetupService -a WifiSetupService.Reset
```

#### Good to know
- If you are using a Raspberry 3 ensure you are connecting to 2Ghz Wifi
- If your passprase contains special character use Base64 encoding
- After you have executed the command unplug the ethernet cable! To observe the connectivity state you should connect an external display (e.g. a monitor) using the HDMI output of your device

#### Troubleshooting
- https://stackoverflow.com/questions/46312043/android-things-raspberry-pi-fails-to-connect-to-wifi
- https://stackoverflow.com/questions/46225713/wifi-setup-not-possible-on-raspberry-pi-3

### Adding an external display with touch input

I don't want to connect a monitor all the time and I also want to use a display  my final device which supports touch gestures like clicks and scrolling.

I'm using a 7" display with the resolution 1024x600. For further information check [this link](http://www.waveshare.com/product/mini-pc/raspberry-pi/7inch-hdmi-lcd-c.htm)

I had or have still some issues with connecting this display.

#### Good to know
- Android Things 0.5.1 should detect your external automatically. **BUT** in my case I had/have some isseues
- If you are using Android Things 0.4.1. you can easily edit the `config.txt` file like described here: http://www.waveshare.com/wiki/7inch_HDMI_LCD_(C)#How_to_distinguish

#### Troubleshooting
- https://stackoverflow.com/questions/46286945/7-lcd-display-not-working-with-android-things
- https://raspberrypi.stackexchange.com/questions/72720/7-lcd-display-not-working-with-android-things



## APIs

** *in progress* **
- Weather: https://openweathermap.org/city/2849483
- Sunrise and Sunset: https://www.programmableweb.com/api/sunrise-and-sunset-times
