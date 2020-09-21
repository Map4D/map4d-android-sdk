# POI Overlay

Lớp MFPOIOverlay cho phép người dùng thêm vào các đối tượng POIs theo tile.

## 1. MFPOIOverlay

Lớp MFPOIOverlayOptions dùng để tạo các option cho đối tượng MFPOIOverlay. Muốn thêm một MFPOIOverlay vào map thì ta phải tạo một đối tượng MFPOIOverlayOptions trước.

```java
public class MFPOIOverlayOptions {
    public MFPOIOverlayOptions() // Khởi tạo MFPOIOverlayOptions
    public MFPOIOverlayOptions poiProvider(@NonNull MFPOIProvider poiProvider) // Set poiProvider cho MFPOIOverlayOptions
    public MFPOIOverlayOptions visible(@NonNull boolean visible) // Set giá trị visible cho MFPOIOverlayOptions
}
```

Lớp MFPOIOverlay để quản lý đối tượng POIs overlay
- Properties:
    - visible: cho phép đối tượng MFPOIOverlay ẩn hay hiện

- Methods:
    - remove(): xóa MFPOIOverlay ra khỏi Map
    - clearTileCache(): Reload POI overlay đang hiển thị và xóa cache
    - isVisible(): Kiểm tra xem MFPOIOverlay ẩn hay hiện trên Map
    - setVisible(boolean visible): Set giá trị visible cho MFPOIOverlay
    
```java
public class MFPOIOverlay extends MFLayerOverlay {
    public String getTileUrl(int x, int y, int z, boolean _3dMode) // Trả về url chứa các POI trong một tile theo các tham số truyền vào
    public boolean isVisible()
    public void setVisible(boolean visible)
}
```

## 2. MFPOIProvider

Là interface với hàm getTile trả về url của các POI chứa trong một Tile.

```java
public interface MFPOIProvider {
    String getTile(int x, int y, int zoom, boolean _3dMode);
}
```

## 3. Tạo POI Overlay

Để thêm một MFPOIOverlay vào Map thì ta thực hiện như sau:

```java
MFPOIProvider poiProvider = new MFUrlPOIProvider() {
    @Override
    public String getPOIUrl(int x, int y, int zoom, boolean _3dMode) {
        return "https://your-domain/api/" + zoom + "/" + x + "/" + y;
    }
};
map4D.addPOIOverlay(new MFPOIOverlayOptions().poiProvider(poiProvider));
```

## 4. API Response
Để sử dụng được tính năng POI Overlay trên Map4D Map SDK, bạn cần 1 API nhận 3 thông số của một `Map Tile` là `x`, `y`, `zoom` và dữ liệu trả về kiểu `JSON` theo cấu trúc sau:

```json
{
  "code": "ok",
  "result": {
    "places": [
      {
        "id": "id",
        "name": "name",
        "types": [
          "bank"
        ],
        "location": {
          "lng": 108.2136443,
          "lat": 16.0698656
        },
        "rank": {
          "value": 1000113.0
        },
        "icon": {
          "type": "bank",
          "url": null,
          "color": "916546FF"
        }
      },
      {
        ...
      }
    ]
  }
}
```

License
-------

Copyright (C) 2020 IOT Link Ltd. All Rights Reserved.
