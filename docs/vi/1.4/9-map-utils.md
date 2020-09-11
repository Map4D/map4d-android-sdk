# Map Utils

## I. Projection
Lớp Projection cho phép người dùng thực hiện các phép chiếu

### 1. Khởi tạo lớp Projection

```java
  MFProjection projection = map4D.getProjection();
```

### 2. Chuyển đổi từ toạ độ LatLng sang toạ độ Screen

```java
  public Point pointForCoordinate(@NonNull MFLocationCoordinate location)
```

```java
  Point point = projection.pointForCoordinate(new MFLocationCoordinate(10.771783, 106.700763));
```

### 3. Chuyển đổi từ toạ độ LatLng sang toạ độ Screen với elevation (meter)

```java
  public Point pointForCoordinate(@NonNull MFLocationCoordinate location, double elevation)
```

```java
  MFLocationCoordinate target = new MFLocationCoordinate(10.771783, 106.700763);
  float elevation = 10;
  Point screenCoordinate = projection.pointForCoordinate(target, elevation);
```

### 4. Chuyển đổi từ toạ độ LatLng sang toạ độ Screen với Camera position

```java
  public Point pointForCoordinate(@NonNull MFLocationCoordinate location,@NonNull MFCameraPosition cameraPosition)
```

- CameraPosition ở đây là vị trí camera mà ta muốn làm tâm của map.
- elevation mặc định = 0 và is3DMode = false

```java
  MFProjection projection = map4D.getProjection();
  MFLocationCoordinate latLng = new MFLocationCoordinate(10.772302, 106.701901);
  MFCameraPosition newCameraPosition = new MFCameraPosition.Builder().target(new MFLocationCoordinate(16.059790, 108.223986)).tilt(0).bearing(0).zoom(17).build();
  Point point = projection.pointForCoordinate(latLng, newCameraPosition);
```

### 5. Chuyển đổi từ toạ độ LatLng sang toạ độ Screen với Camera Position, elevation and is3DMode

- CameraPosition ở đây là vị trí camera mà ta muốn làm tâm của map.

```java
public Point pointForCoordinate(@NonNull MFLocationCoordinate location,@NonNull MFCameraPosition cameraPosition)
```

```java
  MFProjection projection = map4D.getProjection();
  MFLocationCoordinate latLng = new MFLocationCoordinate(10.772302, 106.701901);
  MFCameraPosition newCameraPosition = new MFCameraPosition.Builder().target(new MFLocationCoordinate(16.059790, 108.223986)).tilt(40).bearing(30).zoom(17).build();
  Point point = projection.pointForCoordinate(latLng, newCameraPosition, 100, true);
```

### 6. Chuyển đổi từ toạ độ Screen sang toạ độ LatLng

```java
  public MFLocationCoordinate coordinateForPoint(@NonNull Point point)
```

```java
  Point point = new Point(100, 100);
  MFLocationCoordinate latLang = projection.coordinateForPoint(point);
```

### 7. Chuyển đổi từ toạ độ Screen sang toạ độ LatLng với elevation (meter)

```java
  public MFLocationCoordinate coordinateForPoint(@NonNull Point point, double elevation)
```

```java
  Point point = new Point(100, 100);
  float elevation = 10;
  MFLocationCoordinate latLang = projection.coordinateForPoint(point, elevation);
```

### 8. Chuyển đổi từ toạ độ Screen sang toạ độ LatLng với Camera Position

```java
public MFLocationCoordinate coordinateForPoint(@NonNull Point point, @NonNull MFCameraPosition cameraPosition)
```

- CameraPosition ở đây là vị trí camera mà ta muốn làm tâm của map.
- elevation mặc định = 0 và is3DMode = false

```java
  Point point = new Point(100, 100);
  MFCameraPosition newCameraPosition = new MFCameraPosition.Builder().target(new MFLocationCoordinate(16.059790, 108.223986)).tilt(0).bearing(0).zoom(17).build();
  MFLocationCoordinate latLang = projection.coordinateForPoint(point, newCameraPosition);
```

### 9. Chuyển đổi từ toạ độ Screen sang toạ độ LatLng với Camera Position, elevation and is3DMode

```java
  public MFLocationCoordinate coordinateForPoint(@NonNull Point point, @NonNull MFCameraPosition cameraPosition, double elevation, boolean is3DMode)
```

- CameraPosition ở đây là vị trí camera mà ta muốn làm tâm của map.

```java
  Point point = new Point(100, 100);
  float elevation = 10;
  MFCameraPosition newCameraPosition = new MFCameraPosition.Builder().target(new MFLocationCoordinate(16.059790, 108.223986)).tilt(0).bearing(0).zoom(17).build();
  MFLocationCoordinate latLang = projection.coordinateForPoint(point, newCameraPosition, elevation, true);
```

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
