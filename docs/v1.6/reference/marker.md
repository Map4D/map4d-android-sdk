# Marker reference

### MFMarker Class

Tạo Marker với các **MFMarkerOptions** được chỉ định

<!-- tabs:start -->
#### ** Kotlin **

```kotlin
override fun onMapReady(map4D: Map4D) {  
    val markerOptions = MFMarkerOptions() 
        .position(MFLocationCoordinate(10.793113, 106.720739))  
        .snippet("Trung Tâm Hành Chính").title("Quận Bình Thạnh")  
    val marker = map4D?.addMarker(markerOptions)  
}
```
#### ** Java **

```java
@Override  
public void onMapReady(final Map4D map4D) {  
    MFMarkerOptions marker = new MFMarkerOptions();  
    marker.position(new MFLocationCoordinate(10.793113, 106.720739));  
    marker.snippet("Trung Tâm Hành Chính").title("Quận Bình Thạnh");  
    map4D.addMarker(marker);  
}
```
<!-- tabs:end -->

**Methods**

| Name                         | Parameters                              | Return Value | Description                                                                            |
|------------------------------|:---------------------------------------:|:------------:|----------------------------------------------------------------------------------------|
| **getAnchorU**               | `none`                                  | void       | Get điểm neo của Marker theo chiều x                                                     |
| **getAnchorV**               | `none`                                  | void       | Get điểm neo của Marker theo chiều y                                                     |
| **getElevation**             | `none`                                  | double       | Get độ cao của marker                                                                  |
| **getIcon**                  | `none`                                  |[MFBitmapDescriptor](/reference/marker?id=MFBitmapDescriptor)| Get hình ảnh đơn giản đã set cho marker trước đó|                                                                 ||
| **getIconView**              | `none`                                  | View         | Get hình ảnh custom đã set cho marker trước đó                                         |
| **getPosition**              | `none`                                  | [MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)| Get vị trí của marker         |
| **getRotation**              | `none`                                  | double       | Get góc quay của marker trên bản đồ                                                    |
| **getSnippet**               | `none`                                  | string       | Get mô tả của marker                                                                   |
| **getTitle**                 | `none`                                  | string       | Get tiêu đề của maker                                                                  |
| **getWindowAnchorU**         | `none`                                  | float        | Get điểm neo cho bảng thông tin marker theo chiều x                                    |
| **getWindowAnchorV**         | `none`                                  | float        | Get điểm neo cho bảng thông tin marker theo chiều y                                    |
| **hideInfoWindow**           | `none`                                  | void         | Ẩn bảng thông tin của marker                                                           |
| **isDraggable**              | `none`                                  | boolean      | Kiểm tra xem marker có thể kéo trên bản đồ hay không                                   |
| **isInfoWindowShown**        | `none`                                  | boolean      | Kiểm tra xem bảng thông tin của marker có hiển thị hay không                           |
| **isTouchable**              | `none`                                  | boolean      | Kiểm tra xem hiện tại có thể tương tác với marker không                                |
| **isVisible**                | `none`                                  | boolean      | Kiểm tra xem hiện tại marker có hiển thị hay không                                     |
| **setDraggable**             | boolean                                 | void         | Cho phép marker có được kéo trên bản đồ hay không                                      |
| **setIcon**                  | [MFBitmapDescriptor](/reference/marker?id=MFBitmapDescriptor)|void| Set một hình ảnh đơn giản cho marker để thay cho hình ảnh mặc định          |
| **setIconView**              | View                                    | void         | Set một hình ảnh custom cho marker để thay cho icon và hình ảnh mặc định               |
| **setPosition**              | [MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)|void| Set vị trí cho marker                                              |
| **setRotation**              | double                                  | void         | Set góc quay của marker trên bản đồ theo đơn vị độ                                     |
| **setSnippet**               | string                                  | void         | Set mô tả của marker                                                                   |
| **setTitle**                 | string                                  | void         | Set tiêu đề bảng thông tin của marker                                                  |
| **setElevation**             | double                                  | void         | Set độ cao cho marker so với mực nước biển, đơn vị là mét                              |
| **setTouchable**             | boolean                                 | void         | Set có thể tương tác với marker không                                                  |
| **setAnchor**                | float and float                         | void         | Set điểm neo cho marker                                                                |
| **setWindowAnchor**          | float and float                         | void         | Set điểm neo cho bảng thông tin marker                                                 |
| **showInfoWindow**           | `none`                                  | void         | Hiện bảng thông tin của marker                                                         |
| **setZIndex**                | float                                   | `none`       | Set giá trị zIndex cho Marker                                                          |
| **getZIndex**                | `none`                                  | float        | Get giá trị zIndex hiện tại của Marker                                                 |

### Marker Options

`MFMarkerOptions` class

Đối tượng MFMarkerOptions dùng để xác định các thuộc tính dùng cho Marker.

**Properties**

| Name                       | Type                | Description                                                                                                                                                           |
|----------------------------|:-------------------:|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **position**               |[MFLocationCoordinate](/reference/coordinates?id=MFLocationCoordinate)| chỉ định một **MFLocationCoordinate** để xác định vị trí ban đầu của Marker.                                         |
| **icon**                   |[MFBitmapDescriptor](/reference/marker?id=MFBitmapDescriptor)| Set một hình ảnh đơn giản cho marker.                                                                               |
| **iconView**               | View                | Set hình ảnh custom cho marker                                                                                                                                        |
| **anchorU**                | double              | Xác định điểm neo cho Marker theo chiều x                                                                                                                             |
| **anchorV**                | double              | Xác định điểm neo cho Marker theo chiều y                                                                                                                             |
| **visible**                | boolean             | xác định Marker có thể ẩn hay hiện trên bản đồ. Giá trị mặc định là **true**.                                                                                         |
| **touchable**              | boolean             | cho phép người dùng có thể tương tác với Marker trên bản đồ hay không. Giá trị mặc định là **true**                                                                   |
| **elevation**              | double              | chỉ định độ cao của Marker so với mực nước biển, đơn vị là mét. Giá trị mặc định là **0**                                                                             |
| **zIndex**                 | float               | chỉ định thứ tự chồng nhau giữa các Marker với nhau hoặc giữa Marker với các đối tượng khác trên bản đồ. Giá trị mặc định là **1.0f**                                    |
| **windowAnchorU**          | float               | xác định điểm neo bảng thông tin của Marker theo chiều x. Bảng thông tin này sẽ hiện lên khi click vào Marker.                                                        |
| **windowAnchorV**          | float               | xác định điểm neo bảng thông tin của Marker theo chiều y. Bảng thông tin này sẽ hiện lên khi click vào Marker.                                                        |
| **title**                  | string              | chỉ định tiêu đề của Marker. Tiêu đề sẽ được hiển thị ở dòng đầu tiên của bảng thông tin Marker.                                                                      |
| **snippet**                | string              | mô tả thông tin ngắn gọn cho Marker. Snippet sẽ được hiển thị ở bẳng thông tin của Marker và phía dưới dòng tiêu đề.                                                  |
| **rotation**               | number              | chỉ định góc quay của Marker theo đơn vị là Độ. Giá trị mặc định là **0**                                                                                             |
| **draggable**              | boolean             | cho phép người dùng có thể kéo Marker trên bản đồ hay không. Giá trị mặc định là **false**                                                                            |
| **userData**               | Object              | truyền vào userData mà người dùng định nghĩa                                                                                                                                                |

### MFBitmapDescriptor

Là một đối tượng lưu trữ hình ảnh.

`MFBitmapDescriptor` class

- Tạo MFBitmapDescriptor từ **MFBitmapDescriptorFactory**

<!-- tabs:start -->
#### ** Kotlin **
```kotlin
MFBitmapDescriptorFactory.fromResource(R.drawbable.example)
```
#### ** Java **
```java
MFBitmapDescriptorFactory.fromResource(R.drawbable.example);
```
<!-- tabs:end -->
