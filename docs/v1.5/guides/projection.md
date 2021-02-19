# Projection

Lớp MFProjection được dùng để chuyển đổi giữa vị trí trên màn hình và tọa độ địa lý trên bề mặt trái đất và ngược lại.

## 1. Khởi tạo lớp Projection

<!-- tabs:start -->
#### ** Java **

```java
private Map4D map4D;

MFProjection projection = map4D.getProjection();
```

#### ** Kotlin **

```kotlin
private lateinit var map4D: Map4D

var projection = map4D.projection
```
<!-- tabs:end -->

## 2. Chuyển đổi từ toạ độ LatLng sang tọa độ Screen

Để chuyển từ tọa độ LatLng sang tọa độ Screen ta dùng phương thức `pointForCoordinate` của class `MFProjection`

<!-- tabs:start -->
#### ** Java **

```java
private Map4D map4D;

MFProjection projection = map4D.getProjection();
MFLocationCoordinate location = new MFLocationCoordinate(10.771783, 106.700763);
MFCameraPosition cameraPosition = new MFCameraPosition.Builder().target(location).zoom(17).tilt(15).build();

// Chuyển đổi từ toạ độ LatLng sang toạ độ Screen.
Point point = projection.pointForCoordinate(location);

//Chuyển đổi từ toạ độ LatLng sang toạ độ Screen với elevation (meter)
Point pointElevation = projection.pointForCoordinate(location, 10.0);

// Chuyển đổi từ toạ độ LatLng sang toạ độ Screen với camera.
Point pointWithCamera = projection.pointForCoordinate(location, cameraPosition);

// Chuyển đổi từ toạ độ LatLng sang toạ độ Screen với camera và elevation (meter) và mode
Point point3DWithCamera = projection.pointForCoordinate(location, cameraPosition, 10.0, true);
```

#### ** Kotlin **

```kotlin
private lateinit var map4D: Map4D

var projection = map4D.projection
var location = MFLocationCoordinate(10.771783, 106.700763)
var cameraPosition = MFCameraPosition.Builder().target(location).zoom(17.0).tilt(15.0).build()

// Chuyển đổi từ toạ độ LatLng sang toạ độ Screen.
var point: Point = projection.pointForCoordinate(location)

//Chuyển đổi từ toạ độ LatLng sang toạ độ Screen với elevation (meter)
var pointElevation: Point = projection.pointForCoordinate(location, 10.0)

// Chuyển đổi từ toạ độ LatLng sang toạ độ Screen với camera.
var pointWithCamera: Point = projection.pointForCoordinate(location, cameraPosition)

// Chuyển đổi từ toạ độ LatLng sang toạ độ Screen với camera và elevation (meter) và mode
var point3DWithCamera: Point = projection.pointForCoordinate(location, cameraPosition, 10.0, true)
```
<!-- tabs:end -->

## 3. Chuyển đổi từ toạ độ Screen sang toạ độ LatLng

Để chuyển từ tọa độ Screen sang tọa độ LatLng ta dùng phương thức `coordinateForPoint` của class `MFProjection`

<!-- tabs:start -->
#### ** Java **

```java
private Map4D map4D;

MFProjection projection = map4D.getProjection();
Point point = new Point(100, 100);
MFLocationCoordinate location = new MFLocationCoordinate(10.771783, 106.700763);
MFCameraPosition cameraPosition = new MFCameraPosition.Builder().target(location).zoom(17).tilt(15).build();

// Chuyển đổi từ toạ độ Screen sang toạ độ LatLng.
MFLocationCoordinate coordinate = projection.coordinateForPoint(point);

// Chuyển đổi từ toạ độ Screen sang toạ độ LatLng với elevation (meter)
MFLocationCoordinate coordinateElevation = projection.coordinateForPoint(point, 10.0);

// Chuyển đổi từ toạ độ Screen sang toạ độ LatLng với camera.
MFLocationCoordinate coordinateWithCamera = projection.coordinateForPoint(point, cameraPosition);

// Chuyển đổi từ toạ độ Screen sang toạ độ LatLng với camera và elevation (meter) và mode
MFLocationCoordinate coordinate3D = projection.coordinateForPoint(point, cameraPosition, 10.0, true);
```

#### ** Kotlin **

```kotlin
private lateinit var map4D: Map4D

var projection = map4D.projection
var point = Point(100, 100)
var location = MFLocationCoordinate(10.771783, 106.700763)
var cameraPosition = MFCameraPosition.Builder().target(location).zoom(17.0).tilt(15.0).build()

// Chuyển đổi từ toạ độ Screen sang toạ độ LatLng.
var coordinate = projection.coordinateForPoint(point)

// Chuyển đổi từ toạ độ Screen sang toạ độ LatLng với elevation (meter)
var coordinateElevation = projection.coordinateForPoint(point, 10.0)

// Chuyển đổi từ toạ độ Screen sang toạ độ LatLng với camera.
var coordinateWithCamera = projection.coordinateForPoint(point, cameraPosition)

// Chuyển đổi từ toạ độ Screen sang toạ độ LatLng với camera và elevation (meter) và mode
var coordinate3D = projection.coordinateForPoint(point, cameraPosition, 10.0, true)
```
<!-- tabs:end -->

