# Sự kiện Map4D-SDK
Hướng dẫn sử dụng các sự kiện của Map4D SDK.

## 1. Giới thiệu chung

  > Chú ý: Nếu bạn đăng ký sự kiện trên map thì phải remove chúng khi không còn sử dụng để cải thiện hiệu năng cho map.
  Chi tiết hướng dẫn có ở phần 2.

  **1.1. OnCameraMoveStartedListener**
  - Được gọi khi một trong các thông số camera (tâm, góc nghiêng, góc quay, mức zoom) của map chuẩn bị thay đổi.

  **1.2. OnCameraMoveListener**
  - Được gọi khi một trong các thông số camera (tâm, góc nghiêng, góc quay, mức zoom) của map đang thay đổi.

  **1.3. OnCameraIdleListener**
  - Được gọi khi các thông số camera (tâm, góc nghiêng, góc quay, mức zoom) của map kết thúc sự thay đổi.

  **1.4. OnMapClickListener**
  - Được gọi khi click trên map và ko có sự kiện khác ưu tiên hơn được gọi.

  **1.5. OnMapModeChangeListener**
  - Được gọi khi chuyển mode 3D/2D.
  
  **1.6. OnMyLocationClickListener**
  - Được gọi khi click trên mylocation marker và ko có sự kiện khác ưu tiên hơn được gọi.
  
  **1.7. OnObjectClickListener**
  - Được gọi khi click trên map object (building, cây cối, ...) và ko có sự kiện khác ưu tiên hơn được gọi.
  ---

## 2. Hướng dẫn chi tiết
  **2.1. OnCameraMoveStartedListener**
  ```java
    map4D.setOnCameraMoveStartedListener(new Map4D.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int reason) {
                txtMapState.setText(R.string.startedState);
            }
        });
  ```

  - **OnCameraMoveStartedListener**:
  ```java
    public interface OnCameraMoveStartedListener {
        int REASON_GESTURE = 1;

        /**
         * Called when the camera starts moving after it has been idle or when the reason for camera motion has changed.
         *
         * @param reason the reason for the camera change
         */
        void onCameraMoveStarted(int reason);
    }
  ```

  **2.2. OnCameraMoveListener**
  ```java
   map4D.setOnCameraMoveListener(new Map4D.OnCameraMoveListener() {
        @Override
        public void onCameraMove() {
            txtMapState.setText(R.string.movingState);
        }
    });
  ```

  - **OnCameraMoveListener**:
  ```java
    public interface OnCameraMoveListener{
        /**
         * Called repeatedly as the camera continues to move after an onCameraMoveStarted call.
         * This may be called as often as once every frame and should not perform expensive operations.
         */
        void onCameraMove();
    }
  ```
  **2.3. OnCameraIdleListener**
  ```java
    map4D.setOnCameraIdleListener(new Map4D.OnCameraIdleListener() {
        @Override
        public void onCameraIdle() {

            txtMapState.setText(R.string.idleState);
        }
    });
  ```

  - **OnCameraIdleListener**:
  ```java
	public interface OnCameraIdleListener {
        /**
         * Called when camera movement has ended.
         */
        void onCameraIdle();
    }
  ```

	
  **2.4. OnMapClickListener**

```java
   map4D.setOnMapClickListener(new Map4D.OnMapClickListener() {
    @Override
    public void onMapClick(LatLng latLng) {
        txtMapState.setText("map click: " + latLng.getLatitude() + ", " + latLng.getLongitude());
    }
});
```

  - **OnMapClickListener**:
  
```java
public interface OnMapClickListener {
    /**
     * Called when the user clicks on the map view.
     *
     * @param latLng The projected map coordinate the user clicked on.
     */
    void onMapClick(LatLng latLng);
}
  ```
  - *latLng* : vị trí click trên map theo LatLng

**Chú ý:** Tùy vào việc đăng ký eventas cho cái gì thì đối tượng đó được trả về. Thứ tự ưu tiên sẽ là: Marker -> Polyline/ Polygon/ Circle -> Object -> Map (location). Đối với location event thì vị trí click được trả về dưới dạng LatLng


  **2.5. OnMapModeChangeListener**
	
```java
  map4D.setOnMapModeChange(new Map4D.OnMapModeChangeListener() {
    @Override
    public void onMapModeChange(boolean is3DMode) {
        Toast.makeText(getApplicationContext(), is3DMode ? "2D->3D" : "3D->2D", Toast.LENGTH_SHORT).show();
        if (is3DMode) {
            ((Button) findViewById(R.id.btnSwitchMode)).setText(R.string.mode2d);
        }
        else {
            ((Button) findViewById(R.id.btnSwitchMode)).setText(R.string.mode3d);
        }
        mode3D = is3DMode;
    }
});
```

    - **OnMapModeChangeListener**:
  
```java
public interface OnMapModeChangeListener {
        void onMapModeChange(boolean is3DMode);
    }
 ```
	- *is3DMode* : return true nếu đang ở 3D mode hoặc return false nếu đang ở 2D mode

 **2.6. OnMyLocationClickListener**
	
```java
  map4D.setOnMyLocationClickListener(new Map4D.OnMyLocationClickListener() {
    @Override
    public void onMyLocationClick(Location location) {
        Toast.makeText(this, "My Location: " + location.getLatitude() + ", " + location.getLongitude(), Toast.LENGTH_SHORT).show();
    }
});
```

    - **OnMyLocationClickListener**:
  
```java
public interface OnMyLocationClickListener {
        void onMyLocationClick(Location location);
    }
 ```
	- *location* : return vị trí của location marker.

  **2.7. OnObjectClickListener**
	
```java
  map4D.setOnObjectClickListener(new Map4D.OnObjectClickListener() {
    @Override
    public void onObjectClick(MFObject object) {
        Toast.makeText(TileAreaActivity.this, "Object Name:  " + object.getName(), Toast.LENGTH_SHORT).show();
    }
});
```

    - **OnObjectClickListener**:
  
```java
  public interface OnObjectClickListener {
    void onObjectClick(MFObject object);
  }
```
	- *object* : return về object được click.
	- 
**Chú ý:** Tùy vào việc đăng ký eventas cho cái gì thì đối tượng đó được trả về. Thứ tự ưu tiên sẽ là: Marker -> Polyline/ Polygon/ Circle -> Object -> Map (location). Đối với location event thì vị trí click được trả về dưới dạng LatLng

  License
  -------

  Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
