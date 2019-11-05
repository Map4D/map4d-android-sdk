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

  **1.4. OnMapModeChangeListener**
  - Được gọi khi chuyển mode 3D/2D.
  
  **1.5. OnMyLocationClickListener**
  - Được gọi khi click trên mylocation marker và ko có sự kiện khác ưu tiên hơn được gọi.
  
  **1.6. OnMyLocationButtonClickListener**
  - Được gọi khi my location button được click
  
  **1.7. OnMarkerClickListener**
  - Được gọi khi marker được click
  
  **1.8. OnPolylineClickListener**
  - Được gọi khi polyline được click
  
  **1.9. OnPolygonClickListener**
  - Được gọi khi polygon được click
  
  **1.10. OnCircleClickListener**
  - Được gọi khi circle được click
  
  **1.11. OnObjectClickListener**
  - Được gọi khi click trên map object (building, cây cối, ...) và ko có sự kiện khác ưu tiên hơn được gọi.
  
  **1.12. OnMapClickListener**
  - Được gọi khi click trên bản đồ (không click vào polygon, polyline...). 
  ---

## 2. Hướng dẫn chi tiết
  **2.1. OnCameraMoveStartedListener**
  
  ```java
    map4D.setOnCameraMoveStartedListener(new Map4D.OnCameraMoveStartedListener() {
        @Override
        public void onCameraMoveStarted(int reason) {

        }
    });
  ```
  Tham số *reason* sẽ cho chúng ta biết lý do mà map thay đổi. 
  
  **2.2. OnCameraMoveListener**
  ```java
   map4D.setOnCameraMoveListener(new Map4D.OnCameraMoveListener() {
        @Override
        public void onCameraMove() {

        }
    });
  ```

  **2.3. OnCameraIdleListener**
  ```java
    map4D.setOnCameraIdleListener(new Map4D.OnCameraIdleListener() {
        @Override
        public void onCameraIdle() {
            
        }
    });
  ```
 
  **2.4. OnMapModeChangeListener**
  	
  ```java
    map4D.setOnMapModeChange(new Map4D.OnMapModeChangeListener() {
      @Override
      public void onMapModeChange(boolean is3DMode) {
          
      }
  });
  ```
  Tham số *is3DMode* sẽ là true nếu đang ở 3D mode hoặc false nếu đang ở 2D mode


  **2.5. OnMyLocationClickListener**
	
  ```java
  map4D.setOnMyLocationClickListener(new Map4D.OnMyLocationClickListener() {
      @Override
      public void onMyLocationClick(Location location) {
          
      }
  });
  ```
  Tham số *location* sẽ trả về vị trí của location của người dùng ở vị trí hiện tại.

  **2.6. OnMyLocationButtonClickListener**
  	
  ```java
  map4D.setOnMyLocationButtonClickListener(new Map4D.OnMyLocationButtonClickListener() {
      @Override
      public boolean onMyLocationButtonClick() {
          return false;
      }
  });
  ```
  Nếu trả về *true* thì sự kiện mặc định khi nhấn vào nút vào MyLocation sẽ không được thực thi.
  
  Nếu trả về *false* thì sự kiện mặc định khi nhấn vào nút vào MyLocation sẽ được thực thi.
  
  Sự kiện mặc định khi nhân vào nút MyLocation là camera sẽ di chuyển với vị trí của người dùng 


  **2.7. OnMarkerClickListener**
  	
  ```java
  map4D.setOnMarkerClickListener(new Map4D.OnMarkerClickListener() {
      @Override
      public boolean onMarkerClick(MFMarker marker) {
          return false;
      }
  });
  ```
  Tham số *marker* sẽ trả về đối tượng marker mà người dùng click vào.

  Nếu trả về *true* thì sự kiện mặc định khi nhấn vào marker sẽ không được thực thi.
  
  Nếu trả về *false* thì sự kiện mặc định khi nhấn vào marker sẽ được thực thi.
  
  Sự kiện mặc định khi nhấn vào marker sẽ là hiện thị InfoWindow của marker.


  **2.8. OnPolylineClickListener**
  	
  ```java
  map4D.setOnPolylineClickListener(new Map4D.OnPolylineClickListener() {
      @Override
      public void onPolylineClick(MFPolyline polyline) {

      }
  });
  ```
  Tham số *polyline* sẽ trả về đối tượng polyline mà người dùng click vào.


  **2.9. OnPolygonClickListener**
  	
  ```java
  map4D.setOnPolygonClickListener(new Map4D.OnPolygonClickListener() {
      @Override
      public void onPolygonClick(MFPolygon polygon) {

      }
  });
  ```
  Tham số *polygon* sẽ trả về đối tượng polygon mà người dùng click vào.  

  **2.10. OnCircleClickListener**
  	
  ```java
  map4D.setOnCircleClickListener(new Map4D.OnCircleClickListener() {
      @Override
      public void onCircleClick(MFCircle circle) {

      }
  });
  ```
  Tham số *circle* sẽ trả về đối tượng circle mà người dùng click vào.     

  **2.11. OnObjectClickListener**
  	
  ```java
  map4D.setOnObjectClickListener(new Map4D.OnObjectClickListener() {
      @Override
      public void onObjectClick(MFObject object) {

      }
  });
  ```
  Tham số *object* sẽ trả về đối tượng 3D mà người dùng click vào. 


  **2.12. OnMapClickListener**
  	
  ```java
  map4D.setOnMapClickListener(new Map4D.OnMapClickListener() {
      @Override
      public void onMapClick(LatLng latLng) {

      }
  });
  ```
  Tham số *latLng* sẽ trả về vị trí click trên bản đồ theo LatLng.

**Chú ý:** Tùy vào việc đăng ký eventas cho cái gì thì đối tượng đó được trả về. Thứ tự ưu tiên sẽ là: Marker -> Polyline/ Polygon/ Circle -> Object -> Map (location). Đối với location event thì vị trí click được trả về dưới dạng LatLng.

## 3. Thứ tự các layer nhận sự kien click khi chồng lên nhau

- zIndex càng cao thì sẽ nhận được click trước
```java
MFCircle circle = map4D.addCircle(new MFCircleOptions()
                .fillColor("#ffaabb")
                .radius(300)
                .fillAlpha(0.5f)
                .zIndex(5.f)
                .center(new LatLng(10.773143, 106.713472)));
                
MFMarker marker = map4D.addMarker(new MFMarkerOptions()
                .position(new LatLng(10.76777699097427, 106.70235982464658))
                .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.ic_default_marker))
                .zIndex(15.f)
                .title("Title")
                .snippet("Snippet"));
```

- Marker sẽ đc nhận sự kiện click vì marker có zIndex cao hơn.
- Giá trị mặc định của index: building = 0.f, marker = 1.f, polyline, polygon, circle = -1.f

  License
  -------

  Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
