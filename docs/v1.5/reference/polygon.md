# Polygon reference

### Polygon Class

`MFPolygon` class

Tạo Polygon với các options được chỉ định

<!-- tabs:start -->
#### ** Kotlin **
```kotlin
val pointsList = mutableListOf<MFLocationCoordinate>()

fun createPointsList() {
  pointsList.add(MFLocationCoordinate(16.066517, 108.210354))
  pointsList.add(MFLocationCoordinate(16.067243, 108.214077))
  pointsList.add(MFLocationCoordinate(16.065419, 108.214576))
  pointsList.add(MFLocationCoordinate(16.062815, 108.214034))
  pointsList.add(MFLocationCoordinate(16.062434, 108.210772))
  pointsList.add(MFLocationCoordinate(16.066517, 108.210354))
}

fun addPolygonToMap() {
  createPointsList();
  val polygon = map4D.addPolygon(
    MFPolygonOptions()
    .add(*pointsList.toTypedArray())
    .strokeColor(ContextCompat.getColor(context ?: return, R.color.green))
    .strokeWidth(2f)
      .zIndex(100f)
    .fillColor(ContextCompat.getColor(context ?: return, R.color.blueWithAlpha)))
}
addPolygonToMap()
```
#### ** Java **
```java
  private final List<MFLocationCoordinate> pointsList = new ArrayList<>();

  private void createPointsList() {
  	pointsList.add(new MFLocationCoordinate(16.066517, 108.210354));
  	pointsList.add(new MFLocationCoordinate(16.067243, 108.214077));
  	pointsList.add(new MFLocationCoordinate(16.065419, 108.214576));
  	pointsList.add(new MFLocationCoordinate(16.062815, 108.214034));
  	pointsList.add(new MFLocationCoordinate(16.062434, 108.210772));
  	pointsList.add(new MFLocationCoordinate(16.066517, 108.210354));
 }

  private void addPolygonToMap() {
	createPointsList();
	polygon = map4D.addPolygon(new MFPolygonOptions()
	        .add(pointsList.toArray(new MFLocationCoordinate[pointsList.size()]))
	        .strokeColor(ContextCompat.getColor(this, R.color.green))
            .strokeWidth(2)
	        .fillColor(ContextCompat.getColor(this, R.color.blueWithAlpha))));
	}
```
<!-- tabs:end -->

- Parameters:
  - options: [PolygonOptions](/reference/polygon?id=polygon-options) *required*

**Methods**

| Name                         | Parameters                              | Return Value | Description                                                                            |
|------------------------------|:---------------------------------------:|:------------:|----------------------------------------------------------------------------------------|
| **setPoints**                |List<[MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)>| `none`| Set danh sách các điểm tọa độ của polygon                  |
| **getPoints**                | `none` | List<[MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)>| Get danh sách các điểm tọa độ của polygon                |
| **setHoles**                 |List<[MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)>| `none`| Set danh sách các điểm tọa độ của lỗ bên trong polygon                  |
| **getHoles**                 | `none` | List<[MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)>| Get danh sách các điểm tọa độ của lỗ bên trong polygon                |
| **setFillColor**             | @ColorInt int                                  | `none`| Set màu cho polygon theo mã kiểu @ColorInt int                                         |
| **getFillColor**             | `none`                                  | @ColorInt int| Get màu của polygon                                                                    |
| **setStrokeColor**           | @ColorInt int                                  | `none`| Set màu cho đường viền của polygon theo kiểu @ColorInt int                             |
| **getStrokeColor**           | `none`                                  | @ColorInt int| Get màu đường viền của polygon                                                         |
| **setStrokeWidth**           | float                                   | `none`       | Set độ rộng cho đường viền của polygon theo đơn vị point                               |
| **getStrokeWidth**           | `none`                                  | float        | Get độ rộng đường viền của polygon theo đơn vị point                                   |
| **setVisible**               | boolean                                 | `none`       | Ẩn/hiện polygon trên map hay không                                                     |
| **isVisible**                | `none`                                  | boolean      | Get trạng thái ẩn/hiện của polygon                                                     |
| **setTouchable**             | boolean                                 | `none`       | Cho phép có thể tương tác với polygon trên bản đồ hay không                                     |
| **isTouchable**              | `none`                                  | boolean      | Kiểm tra xem có thể tương tác với polygon trên bản đồ hay không                                  |
| **setZIndex**                | float                                   | `none`       | Set giá trị zIndex cho polygon                                                         |
| **getZIndex**                | `none`                                  | float        | Get giá trị zIndex hiện tại của polygon                                                |

### Polygon Options

`MFPolygonOptions` class

Đối tượng PolygonOptions dùng để xác định các thuộc tính dùng cho Polygon.

**Properties**

| Name                         | Type                | Description                                                                                                                                                           |
|------------------------------|:-------------------:|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **points**                   |List<[MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)>| truyền vào danh sách tọa độ **MFLocationCoordinate** để tạo Polygon                                            |
| **holes**                    |List<[MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)>| truyền vào danh sách tọa độ **MFLocationCoordinate** của lỗ bên trong Polygon                                  |
| **fillColor**                | @ColorInt int       | chỉ định màu tô phía trong của Polygon theo kiểu @ColorInt int. Giá trị mặc định là **Color.RED**                                                                     |
| **visible**                  | boolean             | xác định Polygon có thể ẩn hay hiện trên bản đồ. Giá trị mặc định là **true**.                                                                                        |
| **strokeColor**              | @ColorInt int       | chỉ định màu sắc đường viền ngoài cùng của Polygon theo kiểu @ColorInt int. Giá trị mặc định là **Color.BLACK**.                                                      |
| **strokeWidth**              | float               | chỉ định độ rộng đường viền của Polygon theo đơn vị point.                                                                                                            |
| **touchable**                | boolean             | cho phép người dùng có thể tương tác với Polygon trên bản đồ hay không. Giá trị mặc định là **true**.                                                                 |
| **zIndex**                   | float               | chỉ định thứ tự hiển thị giữa các Polygon với nhau hoặc giữa Polygon với các đối tượng khác trên bản đồ. Giá trị mặc định là **-1.0f**.                               |
| **userData**                 | Object              | Kiểu User Data mà người dùng muốn lưu                                                                                                                                 |