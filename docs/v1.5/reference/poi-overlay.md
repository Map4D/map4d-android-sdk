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
| **poiProvider**| MFPOIProvider                           | MFPOIOverlayOptions| set đối tượng TileProvider cho MFPOIOverlayOptions                               |
| **visible**    | boolean                                 | MFPOIOverlayOptions| set giá trị visible cho MFPOIOverlayOptions                                      |
| **prefixId**   | String                                  | MFPOIOverlayOptions| Set giá trị prefixId cho MFPOIOverlayOptions                                     |


## MFUrlPOIProvider class

`MFUrlPOIProvider` abtract class

**Methods**

| Name           | Parameters                              | Return Value | Description                                                                            |
|----------------|-----------------------------------------|--------------|----------------------------------------------------------------------------------------|
| **getPOIUrl** | int, int, int, boolean                   | String       | Trả về là một đường dẫn URL chứa dữ liệu của POI trên Tile ứng với tọa độ x, y, mức zoom và chế độ Map tương ứng|
| **parserPOIData**| String                                |List< MFPOIData >| Biến đổi dữ liệu từ String trả về từ phương thức `getPOIUrl` sang List< MFPOIData >     |

