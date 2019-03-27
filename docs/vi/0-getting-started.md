# Cài đặt Map4D-SDK

## 1. Cài đặt

  1. Thêm vào Gradle:
  
```java
dependencies {
  implementation 'vn.map4d:map4dsdk:1.1.0'
}
```

  2. thêm quyền trong manifest
  
```java
<uses-feature
        android:glEsVersion="0x00020000"
        android:required="true" />

    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

3. thêm access key trong manifest

```java
<application
...
    android:theme="@style/AppTheme">
    <meta-data
        android:name="vn.map4d.map4dsdk.ACCESS_KEY"
        android:value="98fd21346d83bee24dc734231f7609c9" />
...
```

## 2. Tạo map

  - Khai báo 1 fragment để chứa map or MFMapView trong layout

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

**chú ý: cần destroy map khi dùng MFMapView**

```java
 	private MFMapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple3d_map_activity);
        mapView = findViewById(R.id.map3D);
        mapView.getMapAsync(this);
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
    }
}
```

## 3. Giới hạn mức zoom tối đa và tối thiểu
```java
	@Override
    public void onMapReady(Map4D map4D) {
        map4D.setMinZoomPreference(17.f);
        map4D.setMaxZoomPreference(5.f);
    }
```

## 4. Bật tắt chế độ 3D & 2D
Cho phép tắt bật chế độ 2D và 3D

```java
  @Override
    public void onMapReady(Map4D map4D) {
        map4D.enable3DMode(true); // bật chế độ 3D
        map4D.enable3DMode(false); // tắt chế độ 3D chuyển về 2D
    }
```

## 5. Chế độ chuyển 2D và 3D
Cho phép thay đổi chế độ chuyển 2D & 3D của map. Có 4 chế độ:

```java
public enum MFSwitchMode
{
    Default(0),
    Auto2DTo3D(1),
    Auto3DTo2D(2),
    Auto(3),
    Manual(4);

    private int value;

    MFSwitchMode(int value)
    {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
```

```android
 	@Override
    public void onMapReady(Map4D map4D) {
        map4D.setSwitchMode(MFSwitchMode.Auto2DTo3D);
    }
```

- Auto3DTo2D:
  - **Không** tự động chuyển chuyển từ chế độ 2D qua 3D khi điều khiển zoom từ mức zoom < 17 lên mức zoom > 17.
  - Khi map đang ở mức zoom > 17, map ở chế độ 3D thì khi điều khiển zoom xuống zoom < 17, map sẽ tự động chuyển về chế độ 2D.
- Auto2DTo3D:
  - Tự động chuyển chuyển từ chế độ 2D qua 3D khi điều khiển zoom từ mức zoom < 17 lên mức zoom > 17.
  - Khi map đang ở mức zoom > 17, nếu map đang ở chế độ 3D thì khi không cho phép điều khiển zoom xuống mức zoom < 17.
  - Khi map đang ở mức zoom > 17, nếu map đang chế độ 2D, thì map vẫn có thể zoom về mức zoom < 17.
- Auto:
  - Tự động chuyển chuyển từ chế độ 2D qua 3D khi điều khiển zoom từ mức zoom < 17 lên mức zoom >= 17.
  - Tự động chuyển từ chế độ 3D sang 2D khi điều khiển zoom từ mức zoom >= 17 về mức zoom < 17.
- Manual:
  - Khi map đang ở mức zoom >= 17, nếu map đang ở chế độ 3D thì khi không cho phép điều khiển zoom xuống mức zoom < 17.  
- Default:
  - Chế độ mặc định là **Auto3DTo2D**

**Chú ý: các chế độ này chỉ có tác dụng khi người dùng tương tác với map, không ảnh hưởng khi gọi hàm pan, fly hay setCamera**

## 6. Thay đổi trạng thái và lấy các thông số của map.
Cho phép thay đổi các trạng thái và lấy các thông số của map như độ nghiêng, độ xoay, điểm trung tâm, mức zoom hiện tại

```java
  map4D.getCameraPosition();
```

- getCamera:
  - Cho phép lấy thông tin các thông số camera hiện tại của map.

```java
  public boolean is3DMode()
```
- is3DMode: trả về thông tin hiện tại map là 2D or 3D.
  - false: 2D mode
  - true:  3D mode

```java
  public void setMinZoomPreference(double minZoom)
```
- setMinZoomPreference: thiết lập mức zoom tối thiểu của map.


```java
  public void setMaxZoomPreference(double maxZoom)
```
- setMaxZoomPreference: thiết lập mức zoom tối đa của map.

## 7. Thay đổi thời gian của map
Map 4D SDK cho phép người dùng thiết lập thời gian cho map, dữ liệu 3D và các địa điểm sẽ được lấy theo thời gian người dùng thiết lập, mặc định sẽ lấy thời gian hiện tại.

```java
  String givenDateString = "2000-01-01";
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  try {
    Date mDate = sdf.parse(givenDateString);
    map4D.setTime(mDate);
  } catch (ParseException e) {
  }
```
## 8. Di chuyển map
Cho phép di chuyển map đến một vị trí bất kỳ

```java
    public void moveCamera(@NonNull MFCameraUpdate cameraUpdate);
    public void animateCamera(@NonNull MFCameraUpdate cameraUpdate);
    public void animateCamera(@NonNull MFCameraUpdate cameraUpdate, int durationMs);
```

MFCameraUpdate:

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

MFCameraUpdate:

```java
public interface MFCameraUpdate {

    MFCameraPosition getCameraPosition(@NonNull Map4D map4D);
}
```

- **moveCamera**: di chuyển map đến 1 vị trí camera position
  - CameraPosition: di chuyển đến vị trí camera
  - LatLng: vị trí Lat Long
  - LatLngBounds: vị trí LatLng và zoom của LatLngBounds
  - LatLngZoom: vị trí và mức zoom mong muốn
  - ZoomIn: mức zoom hiện tại + 1
  - ZoomOut: mức zoom hiện tại -1
  - ZoomTo: mức zoom mong muốn
- **animateCamera**: di chuyển map đến 1 vị trí camera, bao gồm các loại sau:
  - CameraPosition: di chuyển đến vị trí camera
  - LatLng: vị trí Lat Long
  - LatLngBounds: vị trí LatLng và zoom của LatLngBounds
  - LatLngZoom: vị trí và mức zoom mong muốn
  - ZoomIn: mức zoom hiện tại + 1
  - ZoomOut: mức zoom hiện tại -1
  - ZoomTo: mức zoom mong muốn 

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
