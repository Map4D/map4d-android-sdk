# Map Utils

## I. Projection
Lớp Projection cho phép người dùng thực hiện các phép chiếu

### 1. Khởi tạo lớp Projection

```java
  MFProjection projection = map4D.getProjection();
```

### 2. Chuyển đổi từ toạ độ LatLng sang toạ độ Screen

```java
  Point point = projection.latLngToScreenCoordinate(new LatLng(10.771783, 106.700763));
```

### 3. Chuyển đổi từ toạ độ LatLng sang toạ độ Screen với elevation (meter)

```java
  LatLng target = new LatLng(10.771783, 106.700763);
  float elevation = 10;
  Point screenCoordinate = projection.latLngToScreenCoordinate(target, elevation);
```

### 4. Chuyển đổi từ toạ độ LatLng sang toạ độ Screen với elevation (meter) với zoom

```java
  LatLng target = new LatLng(10.771783, 106.700763);
  float elevation = 10;
  float zoom = 15;
  Point screenCoordinate = projection.latLngToScreenCoordinate(target, elevation, zoom);
```

### 5. Chuyển đổi từ toạ độ Screen sang toạ độ LatLng

```java
  Point point = new Point(100, 100);
  LatLng latLang = projection.screenCoordinateToLatLng(point);
```

### 6. Chuyển đổi từ toạ độ Screen sang toạ độ LatLng với elevation (meter)

```java
  Point point = new Point(100, 100);
  float elevation = 10;
  LatLng latLang = projection.screenCoordinateToLatLng(point, elevation);
```

### 7. Chuyển đổi từ toạ độ Screen sang toạ độ LatLng với elevation (meter) và mức zoom

```java
  Point point = new Point(100, 100);
  float elevation = 10;
  float zoom = 18;
  LatLng latLang = projection.screenCoordinateToLatLng(point, elevation, zoom);
```

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
