# Map4D SDK

Map4D SDK for Android, written in C++, Java.

[![CocoaPods](https://map4d.vn/Content/Client/img/Untitled-1_0000_Right-Mockup--phone-demo-copy.png)](https://map4d.vn) 


## Installation

Use Gradle
```xml
dependencies {
    implementation 'vn.map4d:Map4dTypes:1.0.6'
    implementation 'vn.map4d:Map4dMap:1.4.4'
}
```
Use Maven
```xml
<dependency>
	<groupId>vn.map4d</groupId>
	<artifactId>Map4dMap</artifactId>
	<version>1.4.4</version>
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
            android:name="vn.map4d.map.ACCESS_KEY"
            android:value="TYPE_YOUR_KEY_HERE"/>

    </application>

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
</manifest>

```

2. Create layout

```xml
<vn.map4d.map.core.MFMapView
        android:id="@+id/mapView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        />
```
3. Working with map view (kotlin)

```kotlin
import vn.map4d.map.core.Map4D
import vn.map4d.map.core.OnMapReadyCallback

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
    
     override fun onDestroy() {
        map4D?.onDestroy()
        super.onDestroy()
     }
}
```
## Document References
[1. Getting started](docs/vi/1.4/0-getting-started.md) 

[2. Map user interaction](docs/vi/1.4/1-map-user-interaction.md) 

[3. Map events](docs/vi/1.4/2-map-events.md)

[4. Marker](docs/vi/1.4/3-marker.md)

[5. Polyline](docs/vi/1.4/4-polyline.md)

[6. Polygon](docs/vi/1.4/5-polygon.md)

[7. Circle](docs/vi/1.4/6-circle.md)

[8. Tile area](docs/vi/1.4/7-tile-area.md)

[9. LatLngBounds](docs/vi/1.4/8-lat-lng-bounds.md)

[10. Map utils](docs/vi/1.4/9-map-utils.md)

[11. 3D objects](docs/vi/1.4/10-3d-objects.md)

[12. Effect](docs/vi/1.4/11-effect-map.md)

[13. Place](docs/vi/1.4/12-place.md)

[14. Building](docs/vi/1.4/MFBuilding.md)

[15. POI](docs/vi/1.4/MFPOI.md)

[16. Tile Overlay](docs/vi/1.4/MFTileOverlay.md)

[17. Building Overlay](docs/vi/1.4/MFBuildingOverlay.md)

[18. POI Overlay](docs/vi/1.4/MFPOIOverlay.md)

[19. Ground Overlay](docs/vi/1.4/MFGroundOverlay.md)

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
