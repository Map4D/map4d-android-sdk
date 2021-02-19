# Map4D SDK

Map4D SDK for Android, written in C/C++, Java.  

> Map4D Android SDK cho phép bạn tùy chỉnh bản đồ với nội dung để hiển thị trên thiết bị android hỗ trợ opengl 2.0   
Map4D Android SDK không chỉ mang hình ảnh thực tế lên trên bản đồ 3D, ngoài ra còn cho phép tương tác và điều chỉnh các đối tượng 3D của bạn  

[![Map4D Android SDK](https://docs.map4d.vn/map4d-android-sdk/resource/overView.png)](https://map4d.vn) 

## Installation

<!-- tabs:start -->
#### ** Gradle **

```xml
dependencies {
  implementation 'vn.map4d:Map4dTypes:1.0.6'
  implementation 'vn.map4d:Map4dMap:1.5.0'
}
```

#### ** Maven **

```xml
<dependency>
	<groupId>vn.map4d</groupId>
	<artifactId>Map4dMap</artifactId>
	<version>1.5.0</version>
	<type>pom</type>
</dependency>
```
<!-- tabs:end -->

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

<!-- tabs:start -->
#### ** Java **

```java
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{ 
    
    private MFMapView mapView;
	private Map4D map4D;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple3d_map_activity);
        mapView = findViewById(R.id.map3D);
        mapView.getMapAsync(this); 
    }
  
    @Override
    public void onMapReady(Map4D map4D) { 
        map4D.enable3DMode(true);
		// Your code
    }
      
    @Override
    protected void onDestroy() { 
        mapView.onDestroy(); 
        super.onDestroy();
    }
}
```

#### ** Kotlin **

```kotlin
import vn.map4d.map.core.Map4D
import vn.map4d.map.core.OnMapReadyCallback

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

	private var map4D: Map4D? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(map4D: Map4D?) {
        map4D?.enable3DMode(true)
        // Your code
    }
    
     override fun onDestroy() {
        map4D?.onDestroy()
        super.onDestroy()
     }
}
```
<!-- tabs:end -->

> **Chú ý:** Khi dùng MFMapView thì cần phải destroy view để tránh trường hợp leak memory