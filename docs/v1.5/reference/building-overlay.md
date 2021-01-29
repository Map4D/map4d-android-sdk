# Building Overlay Reference

## BuildingOverlay class

`MFBuildingOverlay` class

**Methods**

| Name           | Parameters                              | Return Value | Description                                                                            |
|----------------|-----------------------------------------|--------------|----------------------------------------------------------------------------------------|
| **setVisible** | boolean                                 | `none`       | Ẩn/hiện Building Overlay trên map                                                      |
| **isVisible**  | `none`                                  | boolean      | Get trạng thái ẩn/hiện của Building Overlay                                            |
| **getPrefixId**| `none`                                  | String       | Get giá trị prefix id của Building Overlay                                             |

## MFBuildingOverlayOptions class

`MFBuildingOverlayOptions` class

**Constructor**

Để tạo một đối tượng `MFBuildingOverlayOptions` ta làm như sau:

<!-- tabs:start -->
#### ** Java **

```java
MFBuildingOverlayOptions options = new MFBuildingOverlayOptions()
```

#### ** Kotlin **

```kotlin
var options = MFBuildingOverlayOptions()
```
<!-- tabs:end -->

**Methods**

| Name           | Parameters                              | Return Value | Description                                                                            |
|----------------|-----------------------------------------|--------------|----------------------------------------------------------------------------------------|
| **buildingProvider**| MFBuildingProvider                 | MFBuildingOverlayOptions| set đối tượng MFBuildingProvider cho MFBuildingOverlayOptions                               |
| **visible**    | boolean                                 | MFBuildingOverlayOptions| set giá trị visible cho MFBuildingOverlayOptions                                      |
| **prefixId**   | String                                  | MFBuildingOverlayOptions| Set giá trị prefixId cho MFBuildingOverlayOptions                                     |


## MFUrlBuildingProvider class

`MFUrlBuildingProvider` abtract class

**Methods**

| Name           | Parameters                              | Return Value | Description                                                                            |
|----------------|-----------------------------------------|--------------|----------------------------------------------------------------------------------------|
| **getBuildingUrl** | int, int, int                       | String       | Trả về là một đường dẫn URL chứa dữ liệu của POI trên Tile ứng với tọa độ x, y và mức zoom tương ứng|
| **parserBuildingData**| String                           |List< MFBuildingData >| Biến đổi dữ liệu từ String trả vê từ phương thức `getBuildingUrl()` sang List< MFBuildingData >|

