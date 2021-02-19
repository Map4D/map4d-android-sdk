# Polyline reference

### Polyline Class

`MFPolyline` class

- Tạo Polyline với các options được chỉ định

<!-- tabs:start -->
#### ** Kotlin **
```kotlin

  val latLngList = mutableListOf<MFLocationCoordinate>()
  latLngList.add(MFLocationCoordinate(16.067218, 108.213916))
  latLngList.add(MFLocationCoordinate(16.066496, 108.210311))
  latLngList.add(MFLocationCoordinate(16.064877, 108.210397))
  latLngList.add(MFLocationCoordinate(16.059980, 108.211137))
  latLngList.add(MFLocationCoordinate(16.059516, 108.208358))
  latLngList.add(MFLocationCoordinate(16.067218, 108.213916))
  val polyline = map4D?.addPolyline(
    MFPolylineOptions().add(*latLngList.toTypedArray())
    .color(ContextCompat.getColor(context ?: return, R.color.red))
    .width(8.0f))
```

#### ** Java **
```java
  private final List<MFLocationCoordinate> latLngList = new ArrayList<>();

  latLngList.add(new MFLocationCoordinate(16.067218, 108.213916));
  latLngList.add(new MFLocationCoordinate(16.066496, 108.210311));
  latLngList.add(new MFLocationCoordinate(16.064877, 108.210397));
  latLngList.add(new MFLocationCoordinate(16.059980, 108.211137));
  latLngList.add(new MFLocationCoordinate(16.059516, 108.208358));
  latLngList.add(new MFLocationCoordinate(16.067218, 108.213916));

  polyline = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new MFLocationCoordinate[latLngList.size()]))
                .color(ContextCompat.getColor(this, R.color.red))
                .width(8);
```
<!-- tabs:end -->

**Methods**

| Name                         | Parameters                              | Return Value | Description                                                                            |
|------------------------------|:---------------------------------------:|:------------:|----------------------------------------------------------------------------------------|
| **setPath**                  | List<[MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)>| Set mảng các điểm tọa độ của polyline                             |
| **getPoints**                | None |List<[MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)>| Get list các điểm tọa độ của polyline                       |
| **setWidth**                 | float                                   | `none`       | Set độ rộng cho polyline                                                               |
| **getWidth**                 | `none`                                  | float        | Get độ rộng của polyline                                                               |
| **setColor**                 | @ColorInt int                           | `none`       | Set màu cho polyline theo kiểu @ColorInt int                                           |
| **getColor**                 | `none`                                  | @ColorInt int          | Get màu của polyline                                                         |
| **setVisible**               | boolean                                 | `none`       | Ẩn/hiện polyline trên map hay không                                                    |
| **isVisible**                | `none`                                  | boolean      | Get trạng thái ẩn/hiện của polyline                                                    |
| **setStyle**                 | MFPolylineStyle                         | `none`       | Set kiểu vẽ cho polyline (có 2 kiểu là: **MFPolyline.Solid** và **MFPolylineStyle.Dotted**)|
| **getStyle**                 | `none`                                  | MFPolylineStyle| Get kiểu vẽ hiện tại của polyline                                                    |
| **setZIndex**                | float                                   | `none`       | Set giá trị zIndex cho polyline                                                        |
| **getZIndex**                | `none`                                  | float       | Get giá trị zIndex hiện tại của polyline                                                |
| **setTouchable**             | boolean                                 | `none`       | Cho phép có được tương tác với polyline trên bản đồ hay không                          |
| **isTouchable**              | `none`                                  | boolean      | Kiểm tra xem có thể tương tác với polyline trên bản đồ hay không                       |


### Polyline Options

`MFPolylineOptions` class

Đối tượng PolylineOptions dùng để xác định các thuộc tính dùng cho Polyline.

**Properties**

| Name                         | Type                | Description                                                                                                                                                           |
|------------------------------|:-------------------:|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **path**                     |List<[MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)>| truyền vào một mảng các tọa độ **MFLocationCoordinate** để tạo Polyline.                                       |
| **width**                    | float               | chỉ định độ rộng của Polyline theo đơn vị point.                                                                                                                      |
| **color**                    | @ColorInt int       | chỉ định màu sắc của Polyline theo kiểu @ColorInt int. Giá trị mặc định là **"Color.BLACK"**.|
| **visible**                  | boolean             | xác định Polyline có thể ẩn hay hiện trên bản đồ. Giá trị mặc định là **true**.                                                                                       |
| **touchable**                | boolean             | cho phép người dùng có thể tương tác với Polyline trên bản đồ hay không. Giá trị mặc định là **true**.                                                                |
| **zIndex**                   | float               | chỉ định thứ tự hiển thị giữa các Polyline với nhau hoặc giữa Polyline với các đối tượng khác trên bản đồ. Giá trị mặc định là **-1.0f**.                             |
| **style**                    | number              | chỉ định Polyline là loại nét liền (**"MFPolylineStyle.Solid"**) hay nét đứt (**"MFPolylineStyle.Dotted"**). Giá trị mặc định là **"MFPolylineStyle.Solid"**          |
| **userData**                 | Object              | Kiểu User Data mà người dùng muốn lưu                                                                                                                                 |