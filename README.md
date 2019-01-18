# Map4D SDK

Map4D SDK for Android, written in C++, Java.

[![CocoaPods](https://map4d.vn/Content/Client/img/Untitled-1_0000_Right-Mockup--phone-demo-copy.png)](https://map4d.vn) 


## Installation

Use Gradle
```xml
dependencies {
    implementation 'vn.map4d:map4dsdk:1.1.0'
}
```
Use Maven
```xml
<dependency>
	<groupId>vn.map4d</groupId>
	<artifactId>map4dsdk</artifactId>
	<version>1.1.0</version>
	<type>pom</type>
</dependency>
```

## Using

1. Provide access key

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="vn.map4d.simplemap">
    <application
        android:theme="@style/AppTheme">

        <meta-data
            android:name="vn.map4d.map4dsdk.ACCESS_KEY"
            android:value="98fd21346d83bee24dc734231f7609c9"/>

    </application>

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
</manifest>

```

2. Create layout

```xml
<vn.map4d.map4dsdk.maps.MFMapView
        android:id="@+id/mapView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        />
```
3. Working with map view (kotlin)

```kotlin
import vn.map4d.map4dsdk.maps.Map4D
import vn.map4d.map4dsdk.maps.OnMapReadyCallback

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(map4D: Map4D?) {
        map4D?.enable3DMode(true)
        //TODO
    }
}
```

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
