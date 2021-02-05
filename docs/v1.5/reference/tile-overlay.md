# Tile Overlay References

## MFTileOverlay class

`MFTileOverlay` class

**Methods**

| Name           | Parameters                              | Return Value | Description                                                                            |
|----------------|-----------------------------------------|--------------|----------------------------------------------------------------------------------------|
| **setVisible** | boolean                                 | `none`       | Ẩn/hiện Tile Overlay trên map                                                          |
| **isVisible**  | `none`                                  | boolean      | Get trạng thái ẩn/hiện của Tile Overlay                                                |
| **setZIndex**  | double                                  | `none`       | Set giá trị zIndex                                                                     |
| **getZIndex**  | `none`                                  | double       | Get giá trị zIndex hiện tại của Tile Overlay                                           |

## MFTileOverlayOptions class

`MFTileOverlayOptions` class

**Constructor**

Để tạo một đối tượng `MFTileOverlayOptions` ta làm như sau:

<!-- tabs:start -->
#### ** Java **

```java
MFTileOverlayOptions options = new MFTileOverlayOptions()
```

#### ** Kotlin **

```kotlin
var options = MFTileOverlayOptions()
```
<!-- tabs:end -->

**Methods**

| Name           | Parameters                              | Return Value | Description                                                                            |
|----------------|-----------------------------------------|--------------|----------------------------------------------------------------------------------------|
| **tileProvider** | MFTileProvider                        | MFTileOverlayOptions| set đối tượng TileProvider cho MFTileOverlayOptions                             |
| **visible**    | boolean                                 | MFTileOverlayOptions| set giá trị visible cho MFTileOverlayOptions                                    |
| **zIndex**     | double                                  | MFTileOverlayOptions| Set giá trị zIndex cho MFTileOverlayOptions                                     |


## MFUrlTileProvider class

`MFUrlTileProvider` abtract class

**Methods**

| Name           | Parameters                              | Return Value | Description                                                                            |
|----------------|-----------------------------------------|--------------|----------------------------------------------------------------------------------------|
| **getTileUrl** | int, int, int, boolean                  | String?      | Trả về là một đường dẫn URL chứa hình ảnh của Tile ứng với tọa độ x, y, mức zoom và chế độ Map tương ứng|

