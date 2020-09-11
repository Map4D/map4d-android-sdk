# Cài đặt Map4D-SDK

## 1. Cài đặt

  1. Thêm vào Gradle:
  
```java
dependencies {
  implementation 'vn.map4d:Map4dTypes:1.0.6'
  implementation 'vn.map4d:Map4dMap:1.4.4'
}
```

  2. Thêm quyền trong manifest
  
```java
<uses-feature android:glEsVersion="0x00020000" android:required="true" />

<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

  3. Thêm access key trong manifest

Để sử dụng map4d Android SDK, bạn cần phải có access key (bạn có thể sử dụng một access key cho các api trên web, iOS, Android). Để nhận được access key vui vòng truy cập đường link sau ([đăng ký key](http://map4d.vn))
```java
<application ...>
    android:theme="@style/AppTheme">
    <meta-data
        android:name=""vn.map4d.map.ACCESS_KEY""
        android:value="${access_key}" />
    ...
</application>
```


## 2. Tạo map
  2.1. Sử dụng fragment 
  - Khai báo 1 MFSupportMapFragment trong layout

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".Simple3DMapActivity">

    <fragment
        android:id="@+id/map3D"
        class="vn.map4d.map.core.MFSupportMapFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</android.support.constraint.ConstraintLayout>
```
  - Khởi tạo map

```java
public class Simple3DMapActivity extends AppCompatActivity implements OnMapReadyCallback{

    private MFSupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple3d_map_activity);
        mapFragment = (MFSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map3D);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(Map4D map4D) {
        map4D.setMinZoomPreference(17.f);
        map4D.enable3DMode(true);
    }
}
```
  2.2 Sử dụng View
  - Khai báo 1 MFMapView trong layout
  
```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".Simple3DMapActivity">
  
      <vn.map4d.map.core.MFMapView
                      android:id="@+id/map3D"
                      android:layout_width="match_parent"
                      android:layout_height="match_parent" />
  
  </android.support.constraint.ConstraintLayout>
```
  - Khởi tạo map
  
```java
public class Simple3DMapActivity extends AppCompatActivity implements OnMapReadyCallback{ 
    
    private MFMapView mapView;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple3d_map_activity);
        mapView = findViewById(R.id.map3D);
        mapView.getMapAsync(this); 
    }
  
    @Override
    public void onMapReady(Map4D map4D) { 
        map4D.setMinZoomPreference(17.f);
        map4D.enable3DMode(true); 
    }
      
    @Override
    protected void onDestroy() { 
        mapView.onDestroy(); 
        super.onDestroy();
    }
}
```

**Chú ý: cần destroy map khi dùng MFMapView**

## 3. Giới hạn mức zoom tối đa và tối thiểu

Có thể giới hạn mức zoom của map nằm trong khoảng [minZoomPreference, maxZoomPreference]. Nếu không set thì giá trị mặc định sẽ là [0, 22].
- Giá trị lớn nhất của mức zoom chỉ có thể là 22
- Giá trị nhỏ nhất của mức zoom chỉ có thể là 0

```java
@Override
public void onMapReady(Map4D map4D) {
    map4D.setMinZoomPreference(17.f);
    map4D.setMaxZoomPreference(5.f);
}
```
Như ví dụ trên thì mức zoom của map chỉ có thể nằm trong khoảng [5, 17]. Mức zoom của map với cài đặt trên thì không thể < 5 và > 17.

## 4. Các chế độ chuyển đổi mode 2D và 3D

- Dùng onMapModeHandler để cho phép chuyển từ từ 2D sang 3D và ngược lại.
- Hàm shouldChangeMapMode cho phép mode khi hàm return true, không cho phép chuyển mode khi return false

```java

map4D.setOnMapModeHandler(new Map4D.OnMapModeHandler() {
            @Override
            public boolean shouldChangeMapMode() {
                return false; // không cho chuyển mode bằng cử chỉ gesture
            }
        });
```

## 5. Lấy các thông số của map.
Cho phép thay đổi các trạng thái và lấy các thông số của map như độ nghiêng, độ xoay, điểm trung tâm, mức zoom hiện tại

```java
map4D.getCameraPosition();
```
Cho phép lấy thông tin các thông số camera hiện tại của map như mức zoom, tâm map, góc nghiêng, góc xoay.

```java
public boolean is3DMode()
```
Trả về thông tin hiện tại map là 2D or 3D.
  - false: 2D mode
  - true:  3D mode
  
```java
MFUiSettings getUiSettings()
```
Trả về các thông số cài đặt giao diện của Map. Từ đối tượng này ta có thể cài đặt các thiết lập cho map như tắt xoay map, tắt/bật MyLocation layer... 

## 6. Di chuyển map
Cho phép di chuyển map đến một vị trí bất kỳ

```java
// Di chuyển map ngay lập tức đến 1 vị trí mới.
public void moveCamera(@NonNull MFCameraUpdate cameraUpdate);
// Di chuyển map đến vị trí mới sử dụng animation. Thời gian di chuyển thì SDK sẽ tự tính.
public void animateCamera(@NonNull MFCameraUpdate cameraUpdate);
```

Chúng ta có thể tạo MFCameraUpdate thông qua MFCameraUpdateFactory.

MFCameraUpdateFactory:

```java
public final class MFCameraUpdateFactory { 
    public static MFCameraUpdate newCameraPosition(@NonNull MFCameraPosition cameraPosition);
    public static MFCameraUpdate newCoordinate(@NonNull MFLocationCoordinate coordinate) ;
    public static MFCameraUpdate newCoordinateBounds(@NonNull MFCoordinateBounds bounds, int padding) ;
    public static MFCameraUpdate newCoordinateZoom(@NonNull MFLocationCoordinate latLng, double zoom);
    public static MFCameraUpdate zoomIn();
    public static MFCameraUpdate zoomOut();
    public static MFCameraUpdate zoomTo(double zoom);
}
```

- Ví dụ

```java
    MFCameraUpdate cameraUpdate = MFCameraUpdateFactory.newCameraPosition(new MFCameraPosition.Builder().target(new MFLocationCoordinate(10.772302, 106.701901)).zoom(17.f).build());
    map4D.animateCamera(cameraUpdate);
```

- newCameraPosition: di chuyển map đến vị trí camera
    - Support tilt and bearing
    - Nếu người dùng ko truyền giá trị thì mặc định là tilt, bearing, zoom, target của camera hiện tại.
- newCoordinate: di chuyển map đến vị trí MFLocationCoordinate(LatLng) mới với mức zoom hiện tại
- newCoordinateBounds: di chuyển map đến vị trị MFLocationCoordinate(LatLng) và mức zoom vừa vặn với MFCoordinateBounds
- newCoordinateZoom: di chuyển map đến vị trí MFLocationCoordinate(LatLng) mới và mức zoom mới
- zoomIn: mức zoom hiện tại +1
- zoomIn: mức zoom hiện tại -1
- zoomTo: di chuyển map tới mức zoom mong muốn

Ví dụ:

```java
MFCameraUpdate cameraUpdate1 = MFCameraUpdateFactory.newCoordinate(new MFLocationCoordinate(10.7677, 106.7023));
MFCameraUpdate cameraUpdate2 = MFCameraUpdateFactory.newCoordinateZoom(new MFLocationCoordinate(10.7677, 106.7023), 22.0);
MFCameraUpdate cameraUpdate3 = MFCameraUpdateFactory.zoomIn();
...
```

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
