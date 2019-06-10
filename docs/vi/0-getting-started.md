# Cài đặt Map4D-SDK

## 1. Cài đặt

  1. Thêm vào Gradle:
  
```java
dependencies {
  implementation 'vn.map4d:map4dsdk:1.1.0'
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
        android:name="vn.map4d.map4dsdk.ACCESS_KEY"
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
        class="vn.map4d.map4dsdk.maps.MFSupportMapFragment"
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
  
      <vn.map4d.map4dsdk.maps.MFMapView
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
Cho phép thay đổi chế độ chuyển 2D & 3D của map. Có 4 chế độ:

```java
public enum MFSwitchMode
{
    Default(0),
    Auto2DTo3D(1),
    Auto3DTo2D(2),
    Auto(3),
    Manual(4);
}
```

```android
@Override
public void onMapReady(Map4D map4D) {
    map4D.setSwitchMode(MFSwitchMode.Auto2DTo3D);
}
```

- Auto3DTo2D:
  - **Không** tự động chuyển chuyển từ chế độ 2D qua 3D khi điều khiển zoom từ mức zoom < 17 lên mức zoom >= 17.
  - Khi map đang ở mức zoom >= 17, map ở chế độ 3D thì khi điều khiển zoom xuống zoom < 17, map sẽ tự động chuyển về chế độ 2D.
- Auto2DTo3D:
  - Tự động chuyển chuyển từ chế độ 2D qua 3D khi điều khiển zoom từ mức zoom < 17 lên mức zoom >= 17.
  - Khi map đang ở mức zoom >= 17, nếu map đang ở chế độ 3D thì khi không cho phép điều khiển zoom xuống mức zoom < 17.
  - Khi map đang ở mức zoom >= 17, nếu map đang chế độ 2D, thì map vẫn có thể zoom về mức zoom < 17.
- Auto:
  - Tự động chuyển chuyển từ chế độ 2D qua 3D khi điều khiển zoom từ mức zoom < 17 lên mức zoom >= 17.
  - Tự động chuyển từ chế độ 3D sang 2D khi điều khiển zoom từ mức zoom >= 17 về mức zoom < 17.
- Manual:
  - Khi map đang ở mức zoom >= 17, nếu map đang ở chế độ 3D thì khi không cho phép điều khiển zoom xuống mức zoom < 17. Map cũng không tự động chuyển về chế độ 3D khi zoom từ mức zoom 17 lên 18.
- Default:
  - Chế độ mặc định là **Auto3DTo2D**

**Chú ý: các chế độ này chỉ có tác dụng khi người dùng tương tác với map, không ảnh hưởng khi gọi hàm animateCamera, moveCamera...**

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
// Di chuyển map đến vị trí mới sử dụng animation. Thời gian di chuyển thì người dùng truyền vào.
public void animateCamera(@NonNull MFCameraUpdate cameraUpdate, int durationMs);
```

Chúng ta có thể tạo MFCameraUpdate thông qua MFCameraUpdateFactory.

MFCameraUpdateFactory:

```java
public final class MFCameraUpdateFactory { 
    public static MFCameraUpdate newCameraPosition(@NonNull MFCameraPosition cameraPosition);
    public static MFCameraUpdate newLatLng(@NonNull LatLng latLng) ;
    public static MFCameraUpdate newLatLngBounds(@NonNull LatLngBounds bounds, int padding) ;
    public static MFCameraUpdate newLatLngZoom(@NonNull LatLng latLng, double zoom);
    public static MFCameraUpdate zoomIn();
    public static MFCameraUpdate zoomOut();
    public static MFCameraUpdate zoomTo(double zoom);
}
```

- newCameraPosition: di chuyển map đến vị trí camera
- newLatLng: di chuyển map đến vị trí LatLng mới với mức zoom hiện tại
- newLatLngBounds: di chuyển map đến vị trị LatLng và mức zoom vừa vặn với LatLngBounds
- newLatLngZoom: di chuyển map đến vị trí LatLng mới và mức zoom mới
- zoomIn: mức zoom hiện tại +1
- zoomIn: mức zoom hiện tại -1
- zoomTo: di chuyển map tới mức zoom mong muốn

Ví dụ:

```java
MFCameraUpdate cameraUpdate1 = MFCameraUpdateFactory.newLatLng(new LatLng(10.7677, 106.7023));
MFCameraUpdate cameraUpdate2 = MFCameraUpdateFactory.newLatLngZoom(new LatLng(10.7677, 106.7023), 22.0);
MFCameraUpdate cameraUpdate3 = MFCameraUpdateFactory.zoomIn();
...
```

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
