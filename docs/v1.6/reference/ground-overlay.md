# Ground Overlay References

## GroundOverlay class

`MFGroundOverlay` class

**Methods**

| Name           | Parameters                              | Return Value | Description                                                                            |
|----------------|-----------------------------------------|--------------|----------------------------------------------------------------------------------------|
| **setVisible** | boolean                                 | `none`       | Ẩn/hiện Ground Overlay trên map                                                        |
| **isVisible**  | `none`                                  | boolean      | Get trạng thái ẩn/hiện của Ground Overlay                                              |
| **setZIndex**  | double                                  | `none`       | Set giá trị zIndex cho Ground Overlay                                                  |
| **getZIndex**  | `none`                                  | double       | Get giá trị zIndex hiện tại của Ground Overlay                                         |
| **getMapUrl**  | `none`                                  | String       | Get giá trị zIndex hiện tại của Ground Overlay                                         |
| **isOverride** | `none`                                  | boolean      | Get giá trị zIndex hiện tại của Ground Overlay                                         |

## MFGroundOverlayOptions class

`MFGroundOverlayOptions` class

**Constructor**

Để tạo một đối tượng `MFGroundOverlayOptions` ta làm như sau:

<!-- tabs:start -->
#### ** Java **

```java
MFGroundOverlayOptions options = new MFGroundOverlayOptions()
```

#### ** Kotlin **

```kotlin
var options = MFGroundOverlayOptions()
```
<!-- tabs:end -->

**Methods**

| Name           | Parameters                              | Return Value | Description                                                                             |
|----------------|-----------------------------------------|--------------|-----------------------------------------------------------------------------------------|
| **groundProvider** | MFGroundProvider                    |MFGroundOverlayOptions| set đối tượng MFGroundProvider cho MFGroundOverlayOptions                       |
| **bounds**     | MFCoordinateBounds                      |MFGroundOverlayOptions| set giá trị bounds cho MFGroundOverlayOptions                                   |
| **override**   | boolean                                 |MFGroundOverlayOptions| set giá trị override cho MFGroundOverlayOptions                                 |
| **mapUrl**     | boolean                                 |MFGroundOverlayOptions| set giá trị mapUrl cho MFGroundOverlayOptions                                   |
| **visible**    | boolean                                 |MFGroundOverlayOptions| set giá trị visible cho MFGroundOverlayOptions                                  |
| **zIndex**     | double                                  |MFGroundOverlayOptions| set giá trị zIndex cho MFGroundOverlayOptions                                   |


## MFUrlGroundProvider class

`MFUrlGroundProvider` abtract class

**Methods**

| Name           | Parameters                              | Return Value | Description                                                                            |
|----------------|-----------------------------------------|--------------|----------------------------------------------------------------------------------------|
| **getGroundUrl** | int, int, int, boolean                | String?      | Trả về là một đường dẫn URL chứa hình ảnh của Tile ứng với tọa độ x, y, mức zoom và chế độ Map tương ứng|

