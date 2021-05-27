# Circle reference

## Circle Class

`MFCircle` class


Tạo Circle từ  MFCircleOptions:

<!-- tabs:start -->
#### ** Kotlin **
```kotlin
 val circle = map4D.addCircle(MFCircleOptions()
                        .center(MFLocationCoordinate(16.066517, 108.210354))
                        .radius(500)
                        .fillColor(ContextCompat.getColor(this, R.color.redWithAlphaThirtyPercent)))
```
#### ** Java **
```java
 MFCircle circle = map4D.addCircle(new MFCircleOptions()
                        .center(new MFLocationCoordinate(16.066517, 108.210354))
                        .radius(500)
                        .fillColor(ContextCompat.getColor(this, R.color.redWithAlphaThirtyPercent)));
```
<!-- tabs:end -->

**Methods**

| Name                         | Parameters                              | Return Value | Description                                                                            |
|------------------------------|:---------------------------------------:|:------------:|----------------------------------------------------------------------------------------|
| **setCenter**                | [MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)| `none`   | Set tạo độ tâm cho circle                                    |
| **getCenter**                | `none` |  [MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate) | Get tọa độ tâm của circle                                    |
| **setFillColor**             | @ColorInt int                           | `none`       | Set màu cho circle kiểu ColorInt.                                                      |
| **getFillColor**             | `none`                                  | @ColorRes int| Get màu của circle                                                                     |
| **setRadius**                | double                                  | `none`       | Set bán kính cho circle theo đơn vị là mét                                             |
| **getRadius**                | `none`                                  | double       | Get bán kính của circle theo đơn vị là mét                                             |
| **setStrokeColor**           | @ColorInt int                           | `none`       | Set màu cho circle theo kiểu ColorInt                                                  |
| **getStrokeColor**           | `none`                                  | @ColorInt int| Get màu của circle                                                                     |
| **setStrokeWidth**           | float                                   | `none`       | Set độ rộng cho đường viền của circle                                                  |
| **getStrokeWidth**           | `none`                                  | float        | Get độ rộng cho đường viền của circle                                                  |
| **setVisible**               | boolean                                 | `none`       | Ẩn/hiện circle trên map                                                                |
| **isVisible**                | `none`                                  | boolean      | Get trạng thái ẩn/hiện của circle                                                      |
| **setZIndex**                | float                                   | `none`       | Set giá trị zIndex cho circle                                                          |
| **getZIndex**                | `none`                                  | float        | Get giá trị zIndex hiện tại của circle                                                 |
| **setTouchable**             | boolean                                 | `none`       | Cho phép có thể tương tác với circle trên bản đồ hay không                             |
| **isTouchable**              | `none`                                  | boolean      | Kiểm tra xem có thể tương tác được với circle trên bản đồ hay không                    |


## Circle Options

`MFCircleOptions` class

Đối tượng MFCircleOptions dùng để xác định các thuộc tính dùng cho Circle.

**Properties**

| Name                       | Type                | Description                                                                                                                                                           |
|----------------------------|:-------------------:|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **center**                 |[MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)| một điểm tọa độ **MFLocationCoordinate** để xác định tâm của Circle.                                                              |
| **radius**                 | double              | chỉ định bán kính của Circle theo đơn vị **mét**.                                                                                                                     |
| **fillColor**              | string              | chỉ định màu sắc của Circle theo kiểu @ColorInt int. Giá trịn mặc định là Color.RED.                                                                                       |
| **visible**                | boolean             | xác định Circle có thể ẩn hay hiện trên bản đồ. Giá trị mặc định là **true**.                                                                                         |
| **strokeColor**            | string              | chỉ định màu sắc của **đường viền Circle** theo kiểu @ColorInt int. Giá trịn mặc định là Color.BLACK.                                                                      |
| **strokeWidth**            | number              | chỉ định độ lớn của **đường viền Circle** theo đơn vị **point**.                                                                                                      |
| **touchable**              | boolean             | cho phép người dùng có thể tương tác với Circle trên bản đồ hay không. Giá trị mặc định là **true**.                                                                 |
| **zIndex**                 | number              | chỉ định thứ tự  hiển thị giữa các Circle với nhau hoặc giữa Circle với các đối tượng khác trên bản đồ. Giá trị mặc định là **-1.0f**.                                    |
| **userData**               | Object              | Kiểu User Data mà người dùng muốn lưu                                                                                                                                 |
