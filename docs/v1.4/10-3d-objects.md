# Đối tượng 3D trên map

## 1. Bật tắt chế độ 3D
Để bật tắt chế độ 3D sử dụng:

```java
  @Override
  public void onMapReady(Map4D map4D) {
      map4D.enable3DMode(true); // bật chế độ 3D
      map4D.enable3DMode(false); // tắt chế độ 3D chuyển về 2D
  }
```
- **enable3dMode**: 
  - *true* : Bật chế độ 3D, tại chế độ này khi map ở mức zoom >= 17 sẽ hiển thị các đối tượng 3D
  - *false* : Tắt chế độ 3D
  
Người dùng có thể bật tắt chế độ 3D thông qua button 3D mode ở bảng điều khiển

## 2. Thay đổi thời gian của map
Map 4D SDK cho phép người dùng thiết lập thời gian cho map, dữ liệu 3D và các địa điểm sẽ được lấy theo thời gian người dùng thiết lập, mặc định sẽ lấy thời gian hiện tại.

Nếu bạn cài đặt thời gian cho map là 1/1/2017 thì tất cả các đối tượng 3D mà có thời gian sau 1/1/2017 thì sẽ không được hiển thị trên bản đồ.

```java
  String givenDateString = "2017-01-01";
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  try {
    Date mDate = sdf.parse(givenDateString);
    map4D.setTime(mDate);
  } catch (ParseException e) {
  }
```

## 3. Các sự kiện trên đối tượng 3D
- Sự kiện click phát sinh khi người dùng click lên đối Building 3D của map4D

```java
    @Override
    public void onMapReady(Map4D map4D) {
        map4D.setOnBuildingClickListener(new Map4D.OnBuildingClickListener() {
            @Override
            public void onBuildingClick(String buidingID, String name, MFLocationCoordinate location) {
                txtMapState.setText("Building:   " + name);
            }
        });
    }
```

- Sự kiện click phát sinh khi người dùng click lên đối tượng Building 3D được người dùng thêm vào thông qua MFBuilding

```java
    @Override
    public void onMapReady(Map4D map4D) {
        map4D.setOnUserBuildingClickListener(new Map4D.OnBuildingClickListener() {
            @Override
            public void onUserBuildingClick(MFBuilding mfBuilding) {
                txtMapState.setText("Building:   " + mfBuilding.getName());
            }
        });
    }
```

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
