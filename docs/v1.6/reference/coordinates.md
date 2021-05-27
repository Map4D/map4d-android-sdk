# Coordinates References

### MFLocationCoordinate

`MFLocationCoordinate` class

**Constructor**

```kotlin
public open class MFLocationCoordinate public constructor(latitude: Double, longitude: Double) : Parcelable, Serializable
```

- Parameters
  - latitude: Double 
  - longitude: Double

Tạo đối tượng LatLng vớ latitude and longitude

<!-- tabs:start -->

#### ** Kotlin **
```kotlin
val locationCoordinate = MFLocationCoordinate(latitude, longitude)
```
#### ** Java **
```java
MFLocationCoordinate locationCoordinate = new MFLocationCoordinate(latitude, longitude);
```
<!-- tabs:end -->

**Properties**

|   Name   | Type   | Description |
|----------|--------|-------------|
| **latitude**  | Double | Tọa độ latitude    |
| **longitude** | Double | Tọa độ longitude   |

**Methods**

| Name          | Parameters        | Return Value | Description                                                             |
|---------------|-------------------|--------------|-------------------------------------------------------------------------|
| **getLatitude**| `none`           | Double       | Lấy giá trị latitude                                                    |
| **setLatitude**| Double           | `none`       | Cài đặt giá trị latitude                                                |
| **getLongitude**| `none`          | Double       | Lấy giá trị longitude                                                   |
| **setLongitude**| Double          | `none`       | Cài đặt giá trị longitude                                               |
| **angle**     | `none`            | Double       | Tính góc giữa LatLng và trục Ox                                         |
| **clone**     | `none`            | MFLocationCoordinate|                                                                  |
| **distance**  | MFLocationCoordinate| Double     | Tính khoảng cách theo đường chim bay giữa 2 location                    |
| **equals**    | Any               | Boolean      | Kiểm tra xem location có giống nhau hay không                           |
| **multiply**  | Double            | MFLocationCoordinate| Tích của location coordinate với 1 số                            |
| **subtract**  | MFLocationCoordinate| MFLocationCoordinate| Hiệu của 2 location                                            |

### MFCoordinateBounds

`MFCoordinateBounds` class

**Constructor**

```java
public MFCoordinateBounds(MFLocationCoordinate southwest, MFLocationCoordinate northeast)
```

- Parameters
  - southwest: [MFLocationCoordinate](#MFLocationCoordinate)
  - northeast: [MFLocationCoordinate](#MFLocationCoordinate)

- Tạo đối tượng MFCoordinateBounds với từ MFCoordinateBounds.Builder

<!-- tabs:start -->
#### ** Kotlin **
```kotlin
    val latLngList: MutableList<MFLocationCoordinate> = ArrayList()
    latLngList.add(MFLocationCoordinate(16.067218, 108.213916))
    latLngList.add(MFLocationCoordinate(16.066496, 108.210311))
    latLngList.add(MFLocationCoordinate(16.064877, 108.210397))
    latLngList.add(MFLocationCoordinate(16.059980, 108.211137))
    latLngList.add(MFLocationCoordinate(16.059516, 108.208358))

    for (latlng in latLngList) {
      val markerOptions = MFMarkerOptions().position(latlng)
      map4D?.addMarker(markerOptions)
    }
    val builder = MFCoordinateBounds.builder().includes(
      listOf(MFLocationCoordinate(16.067218, 108.213916),
            MFLocationCoordinate(16.066496, 108.210311),
            MFLocationCoordinate(16.064877, 108.210397),
            MFLocationCoordinate(16.059980, 108.211137),
            MFLocationCoordinate(16.059516, 108.208358)))
    val coordinateBounds = builder.build()
    val cameraPositionForBounds = map4D?.getCameraPositionForBounds(coordinateBounds, 10)
    map4D?.moveCamera(MFCameraUpdateFactory.newCameraPosition(cameraPositionForBounds ?: return))
```
#### ** Java **
```java
    ArrayList<MFLocationCoordinate> latLngList = new ArrayList();
    latLngList.add(new MFLocationCoordinate(16.067218, 108.213916));
    latLngList.add(new MFLocationCoordinate(16.066496, 108.210311));
    latLngList.add(new MFLocationCoordinate(16.064877, 108.210397));
    latLngList.add(new MFLocationCoordinate(16.059980, 108.211137));
    latLngList.add(new MFLocationCoordinate(16.059516, 108.208358));

    for (MFLocationCoordinate latlng : latLngList ) {
        MFMarkerOptions markerOptions = new MFMarkerOptions().position(latlng);
        map4D.addMarker(markerOptions);
    }
    MFCoordinateBounds.Builder builder = MFCoordinateBounds.builder().includes(
      Arrays.asList(new MFLocationCoordinate(16.067218, 108.213916),
        new MFLocationCoordinate(16.066496, 108.210311),
        new MFLocationCoordinate(16.064877, 108.210397),
        new MFLocationCoordinate(16.059980, 108.211137),
        new MFLocationCoordinate(16.059516, 108.208358)));
    MFCoordinateBounds coordinateBounds = builder.build();
    MFCameraPosition cameraPositionForBounds = map4D.getCameraPositionForBounds(coordinateBounds, 10);
    map4D.moveCamera(MFCameraUpdateFactory.newCameraPosition(cameraPositionForBounds));
```
<!-- tabs:end -->

**Methods**

| Name         | Parameters | Return Value | Description                         |
|--------------|------------|--------------|-------------------------------------|
| builder      | `none`     | MFCoordinateBounds.Builder| tạo instance của MFCoordinateBounds.Builder|
| contains     | [MFLocationCoordinate](#MFLocationCoordinate)| boolean| xác định tọa độ latlng có nằm trong bounds hay không|
| include      | [MFLocationCoordinate](#MFLocationCoordinate)| MFCoordinateBounds| Trả về bounds mới khi thêm location coordinate|
| fromCoordinates| List<[MFLocationCoordinate](#MFLocationCoordinate)>| MFCoordinateBounds| Trả về bounds của danh sách các location coordinate|
| world        | `none`     | MFCoordinateBounds| Trả về bounds của thế giới thực theo Lat Lng|
| getNortheast | `none`     | [MFLocationCoordinate](#MFLocationCoordinate)| Get vị trí phía đông bắc của bounds |
| getSouthwest | `none`     | [MFLocationCoordinate](#MFLocationCoordinate)| Get vị trí phía tây nam của bounds  |


### MFCoordinateBounds.Builder

`MFCoordinateBounds` class

**Methods**

| Name         | Parameters | Return Value | Description                         |
|--------------|------------|--------------|-------------------------------------|
| include      | [MFLocationCoordinate](#MFLocationCoordinate)| MFCoordinatBounds.Builder| thêm 1 location coordinate vào danh sách coordinate      |
| includes     | List<[MFLocationCoordinate](#MFLocationCoordinate)>| MFCoordinatBounds.Builder| thêm 1 location coordinate vào danh sách coordinate|
| build        | `none`     | MFCoordinateBounds| Tạo bounds cho danh sách **MFLocationCoordinate**. Nếu size danh sách nhỏ hơn 2 thì văng exception |                                                                     |