# Map Controls

## 1. Các thao tác trên bản đồ

### Chọn đối tượng hay một vị trí bất kỳ trên map

Chạm vào đối tượng hay một vị trí bất kỳ trên map.

### Di chuyển map

Chạm và giữ ngón tay di chuyển trên map để di chuyển map.

### Xoay map

Dùng hai ngón tay di chuyển theo đường tròn trên map để xoay map.

### Nghiêng map

Dùng hai ngón tay di chuyển lên hoặc xuống cùng hướng để nghiêng map.

### Thu nhỏ hoặc phóng to map

Dùng hai ngón tay thu hẹp/ mở rộng khoảng cách của 2 ngón tay trên **map** để thay đổi mức **zoom**.

- **Thu hẹp:** Giảm mức zoom
- **Mở rộng:** Tăng mức zoom

## 2. Các thuộc tính điều khiển Map

> Bạn có thể tắt các điều khiển mặc định trên map bằng cách gọi các phương thức của lớp `MFUiSettings`, đối tượng của lớp
này là một thuộc tính của lớp `Map4D`

- Các điều khiển sau có thể được bật và tắt theo các phương thức sau:
    - `setMyLocationButtonEnabled()`: Bật tắt nút myLocation mà Map4D Android SDK cung cấp.
    - `setScrollGesturesEnabled()`: Bật tắt cử chỉ di chuyển Map. Nếu được bật, người dùng có thể vuốt để di chuyển map.
    - `setZoomGesturesEnabled()`: Bật tắt các cử chỉ phóng to hoặc thu nhỏ Map. Nếu được bật, người dùng có thể nhấn đúp, dùng hai ngón tay thu hẹp hoặc mở rộng để thay đổi mức zoom của camera.
    - `setTiltGesturesEnabled()`: Bật tắt các cử chỉ nghiêng Map. Nếu được bật, người dùng có thể sử dụng hai ngón tay vuốt dọc xuống hoặc vuốt lên để nghiêng camera.
    - `setRotateGesturesEnabled()`: Bật tắt tất các cử chỉ xoay Map. Nếu được bật, người dùng có thể sử dụng cử chỉ xoay hai ngón tay để xoay camera.

**Chú ý:** Chúng ta có thể bật hoặc tắt tất cả các cử chỉ điều khiển Map thông qua phương thức `setAllGesturesEnabled()`

<!-- tabs:start -->
#### ** Java **

```java
private Map4D map4D;

MFUiSettings settings = map4D.getUiSettings();
settings.setMyLocationButtonEnabled(false);
settings.setScrollGesturesEnabled(false);
settings.setZoomGesturesEnabled(false);
settings.setTiltGesturesEnabled(false);
settings.setRotateGesturesEnabled(false);
settings.setAllGesturesEnabled(false);
```

#### ** Kotlin **

```kotlin
private lateinit var map4D: Map4D

var settings = map4D.getUiSettings()
settings.setMyLocationButtonEnabled(false)
settings.setScrollGesturesEnabled(false)
settings.setZoomGesturesEnabled(false)
settings.setTiltGesturesEnabled(false)
settings.setRotateGesturesEnabled(false)
settings.setAllGesturesEnabled(false)
```
<!-- tabs:end -->

Để cài đặt mức zoom tối đa có thể get Tile từ server sử dụng phương thức `setMaxNativeZoom()`

<!-- tabs:start -->
#### ** Java **

```java
private Map4D map4D;

map4D.setMaxNativeZoom(19)
```

#### ** Kotlin **

```kotlin
private lateinit var map4D: Map4D

map4D.setMaxNativeZoom(19)
```
<!-- tabs:end -->

**Ví dụ:** Nếu bạn cài đặt nativeZoom = 19 thì từ mức zoom > 19 sẽ lấy tile từ mức zoom 19 mà không phải get lại tile mới từ server.

## 3. Các phương thức tương tác trên map

### Giới hạn mức zoom tối đa và tối thiểu

Set mức **zoom** tối đa và tối thiểu thông qua phương thức `setMinZoomPreference()`

<!-- tabs:start -->
#### ** Java **

```java
private Map4D map4D;

map4D.setMinZoomPreference(3)
```

#### ** Kotlin **

```kotlin
private lateinit var map4D: Map4D

map4D.setMinZoomPreference(3)
```
<!-- tabs:end -->

### Bật tắt chế độ 2D và 3D

Sử dụng phương thức `enable3DMode()` để thay đổi mode 2D hoặc 3D của map.

Mức **zoom** tối thiểu ở chế độ 3D là 17.

<!-- tabs:start -->
#### ** Java **

```java
private Map4D map4D;

map4D.enable3DMode(true)
```

#### ** Kotlin **

```kotlin
private lateinit var map4D: Map4D

map4D.enable3DMode(true)
```
<!-- tabs:end -->

### Lấy các tham số của camera

** Lấy các tham số của camera như độ nghiêng, độ xoay, điểm quan sát, mức zoom hiện tại.**

<!-- tabs:start -->
#### ** Java **

```java
private Map4D map4D;

MFCameraPosition cameraPosition = map4D.getCameraPosition();
MFLocationCoordinate target = cameraPosition.getTarget();
double zoom = cameraPosition.getZoom();
double tilt = cameraPosition.getTilt();
double bearing = cameraPosition.getBearing();
```

#### ** Kotlin **

```kotlin
private lateinit var map4D: Map4D

var cameraPosition = map4D.cameraPosition
var target = cameraPosition.target
var zoom = cameraPosition.zoom
var tilt = cameraPosition.tilt
var bearing = cameraPosition.bearing
```
<!-- tabs:end -->

**Kiểm tra mode của map hiện tại là 2D hoặc 3D.**

<!-- tabs:start -->
#### ** Java **

```java
private Map4D map4D;

boolean is3DMode = map4D.is3DMode();
```

#### ** Kotlin **

```kotlin
private lateinit var map4D: Map4D

val is3DMode = map4D.is3DMode
```
<!-- tabs:end -->

Phương thức sẽ trả về một giá trị `boolean`:

- **false:** Map đang ở chế độ 2D.
- **true:** Map đang ở chế độ 3D.

## 4. Di chuyển Map

**Map4D Android SDK** hỗ trợ người dùng di chuyển **Map** tới một vị trí camera mới thông qua 2 phương thức `animateCamera()`
và `moveCamera()`.

**Sử dụng phương thức `animateCamera()`

<!-- tabs:start -->
#### ** Java **

```java
private Map4D map4D;

map4D.animateCamera(MFCameraUpdateFactory.newCoordinateBounds(
    new MFCoordinateBounds(
        new MFLocationCoordinate(16.057814922971613, 108.22065353393553),
        new MFLocationCoordinate(16.064289641988594, 108.2324981689453)),
    10));
```

#### ** Kotlin **

```kotlin
private lateinit var map4D: Map4D

map4D.animateCamera(MFCameraUpdateFactory.newCoordinateBounds(
    MFCoordinateBounds(
        MFLocationCoordinate(16.057814922971613, 108.22065353393553),
        MFLocationCoordinate(16.064289641988594, 108.2324981689453)),
    10))
```
<!-- tabs:end -->

**Sử dụng phương thức `moveCamera()`

<!-- tabs:start -->
#### ** Java **

```java
private Map4D map4D;

map4D.moveCamera(MFCameraUpdateFactory.newCoordinateBounds(
    new MFCoordinateBounds(
        new MFLocationCoordinate(16.057814922971613, 108.22065353393553),
        new MFLocationCoordinate(16.064289641988594, 108.2324981689453)),
    10));
```

#### ** Kotlin **

```kotlin
private lateinit var map4D: Map4D

map4D.moveCamera(MFCameraUpdateFactory.newCoordinateBounds(
    MFCoordinateBounds(
        MFLocationCoordinate(16.057814922971613, 108.22065353393553),
        MFLocationCoordinate(16.064289641988594, 108.2324981689453)),
    10))
```
<!-- tabs:end -->
