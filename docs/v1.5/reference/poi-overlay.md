# POI Overlay References

## POIOverlay class

`MFPOIOverlay` class

**Methods**

| Name           | Parameters                              | Return Value | Description                                                                            |
|----------------|-----------------------------------------|--------------|----------------------------------------------------------------------------------------|
| **setVisible** | boolean                                 | `none`       | Ẩn/hiện POI Overlay trên map                                                           |
| **isVisible**  | `none`                                  | boolean      | Get trạng thái ẩn/hiện của POI Overlay                                                 |
| **getPrefixId**| `none`                                  | String       | Get giá trị prefix id của POI Overlay                                                  |

## MFPOIOverlayOptions class

`MFPOIOverlayOptions` class

**Constructor**

Để tạo một đối tượng `MFPOIOverlayOptions` ta làm như sau:

<!-- tabs:start -->
#### ** Java **

```java
MFPOIOverlayOptions options = new MFPOIOverlayOptions()
```

#### ** Kotlin **

```kotlin
var options = MFPOIOverlayOptions()
```
<!-- tabs:end -->

**Methods**

| Name           | Parameters                              | Return Value | Description                                                                            |
|----------------|-----------------------------------------|--------------|----------------------------------------------------------------------------------------|
| **poiProvider**| MFPOIProvider                           | MFPOIOverlayOptions| set đối tượng MFPOIProvider cho MFPOIOverlayOptions                              |
| **visible**    | boolean                                 | MFPOIOverlayOptions| set giá trị visible cho MFPOIOverlayOptions                                      |
| **prefixId**   | String                                  | MFPOIOverlayOptions| Set giá trị prefixId cho MFPOIOverlayOptions                                     |


## MFUrlTileProvider class

`MFUrlBuildingProvider` abtract class

**Methods**

| Name           | Parameters                              | Return Value | Description                                                                            |
|----------------|-----------------------------------------|--------------|----------------------------------------------------------------------------------------|
| **getBuildingUrl** | int, int, int                       | String       | Trả về là một đường dẫn URL chứa dữ liệu của Building trên Tile ứng với tọa độ x, y và mức zoom tương ứng|
| **parserBuildingData**| String                           |List<MFBuildingData>| Biến đổi dữ liệu từ String trả vê từ phương thức `getBuildingUrl()` sang List<MFPOIData>   |

