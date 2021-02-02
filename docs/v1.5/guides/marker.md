# Marker

Marker dùng để xác định một vị trí trên bản đồ. Cho phép người dùng thêm một điểm ghim trên bản đồ ở một vị trí xác định.

### 1. Thêm một marker
Hàm khởi tạo của lớp **MFMarker** cần truyền vào một đối tượng **MFMarkerOptions** để định nghĩa các thuộc tính ban đầu của
Marker. 

Đối tượng **MFMarkerOptions** được định nghĩa như sau:

```java
public final class MFMarkerOptions {
    private MFLocationCoordinate position;
    
    private MFBitmapDescriptor icon;
    
    private View iconView;
    
    private double anchorU;
    
    private double anchorV;
    
    private boolean visible;
    
    private boolean touchable;
    
    private double elevation;
    
    private float zIndex;
    
    private float windowAnchorU;
    
    private float windowAnchorV;
    
    private String title;
    
    private String snippet;
    
    private double rotation;
    
    private boolean draggable;
    
    private Object userData;
}
  
public final class MFMarker extends Annotation {
  public MFMarker(@NonNull MFMarkerOptions markerOptions, @NonNull AnnotationDelegate annotationDelegate);
  public MFLocationCoordinate getPosition(); // vị trí của marker
  public MFBitmapDescriptor getIcon();
  public double getAnchorU(); // anchorU của Marker
  public double getAnchorV(); // anchorV của Marker
  public boolean isVisible(); // check marker show/hide
  public View getIconView(); // get custom View của marker
  public String getSnippet(); // get value của snippet
  public String getTitle(); // get value của Title
  public double getElevation(); //độ cao của marker
  public float getZIndex(); // zIndex của Marker
  public float getWindowAnchorU(); // anchorU của marker trong khoảng [0, 1]
  public float getWindowAnchorV(); // anchorV của marker trong khoảng [0, 1]
  public void hideInfoWindow(); // ẩn infoWindow
  public boolean isInfoWindowShown(); //check infoWindow show or hide
  public void setPosition(@NonNull final MFLocationCoordinate position); //position của marker on Map
  public void setSnippet(String snippet); //set snippet cho Marker
  public void setTitle(String title); //set Tittle cho marker
  public void setWindowAnchor(float windowAnchorU, float windowAnchorV); // infoWindow Anchor
  public void setVisible(final boolean visible)// show/hide marker
  public void setZIndex(final float zIndex); // hiển thị theo thứ tự index, index càng cao thì vẽ trước
  public void setIconView(@NonNull final View iconView); // set custom View Marker
  public void showInfoWindow(); // hiển thị InfoWindow
  public void remove(); // xóa marker từ map
  public boolean isTouchable(); // lấy giá trị của touchable
  public void setTouchable(boolean touchable); //set giá trị touchable
  public boolean isDraggable(); // lấy giá trị của draggable
  public void setDraggable(boolean draggable); //set giá trị draggable
  public double getRotation(); // lấy giá trị góc quay marker
  public void setRotation(); // set giá trị góc quay marker
}
```

Các thuộc tính của **Marker Options** :
- **position**: chỉ định một **MFLocationCoordinate** để xác định vị trí ban đầu của Marker. 
- **icon**: chỉ định icon kiểu MFBitmapDescriptor cho Marker. Nếu icon == null thì lấy marker mặc định.
- **iconView**: Truyền **custom view** cho marker. Nếu iconView != null thì iconView sẽ thay thế icon.
- **anchorU** : chỉ định điểm neo cho Marker theo chiều x theo tỷ lệ của icon or iconView. Giá trị mặc định là **0.5**
- **anchorV** : chỉ định điểm neo cho Marker theo chiều y theo tỷ lệ của icon or iconView. Giá trị mặc định là **1.0**
- **windowAnchorU** : chỉ định điểm neo của infoWindow theo chiều x với tỷ lệ của infoWindow. Giá trị mặc định là **0.5**
- **windowAnchorV** : chỉ định điểm neo của infoWindow theo chiều y với tỷ lệ của infoWindow. Giá trị mặc định là **0.0**
- **visible** : xác định Marker có thể ẩn hay hiện trên bản đồ. Giá trị mặc định là **true**.
- **touchable** : xác định có thể tương tác với marker hay không. Giá trị mặc định là **true**. Khi không cho phép người
dùng tương tác với Marker thì tất cả các sự kiện liên quan tới Marker từ phía người dùng sẽ không có tác dụng
- **elevation** : chỉ định độ cao của Marker so với mực nước biển, đơn vị là mét. Giá trị mặc định là **0**
- **zIndex** : chỉ định thứ tự hiển thị giữa các Marker với nhau hoặc giữa Marker với các đối tượng khác trên
bản đồ. Giá trị mặc định là **0**
- **title** : chỉ định tiêu đề của Marker. Tiêu đề sẽ được hiển thị ở dòng đầu tiên của bảng thông tin Marker.
- **snippet** : mô tả thông tin ngắn gọn cho Marker. Snippet sẽ được hiển thị ở bẳng thông tin của Marker và
phía dưới dòng tiêu đề.
- **rotation** : chỉ định góc quay của Marker theo đơn vị là Độ. Giá trị mặc định là **0**
- **draggable** : chỉ định marker có drag được hay không. Giá trị mặc định là **false**
- **userData** : chỉ định góc quay của Marker theo đơn vị là Độ. Giá trị mặc định là **0**

Ví dụ sau đây thêm một Marker đơn giản vào bản đồ tại Bình Thạnh, Thành phố Hồ Chí Minh:

<!-- tabs:start -->
#### ** Kotlin **

```kotlin
override fun onMapReady(map4D: Map4D) {  
    val markerOptions = MFMarkerOptions() 
    markerOptions.position(MFLocationCoordinate(10.793113, 106.720739))  
    markerOptions.snippet("Trung Tâm Hành Chính").title("Quận Bình Thạnh")  
    map4D.addMarker(markerOptions)  
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

### 2. Xóa Marker khỏi bản đồ

Để xóa một Marker ra khỏi bản đồ, hãy gọi phương thức **remove()**

<!-- tabs:start -->
#### ** Kotlin **

```kotlin
    marker.remove()
```

#### ** Java **

```java
    marker.remove();
```
<!-- tabs:end -->

> Nếu bạn muốn quản lý một danh sách các Marker, bạn nên tạo một mảng để chứa các Marker đó. Sử dụng mảng này bạn  
có thể gọi phương thức **remove()** lần lượt từng Marker trong mảng khi bạn cần xóa các Marker.

### 3. Tùy chỉnh hình ảnh cho Marker

Bạn có thể chỉ định một icon hoặc một hình ảnh khác để thay thế cho hình ảnh mặc định của Marker.

#### Thay đổi icon mặc đinh với thuộc tính icon

> Nếu bạn muốn thay đổi icon mặc định với icon khác từ image bạn có thể dùng thuộc tính **icon** cho marker.
> Hiện tại icon tạo từ resource chỉ hỗ trợ BitmapDrawable or VectorDrawable

> Chúng ta có thể tạo icon *MFBitmapDescriptor* thông qua *MFBitmapDescriptorFactory* class.

```java
public final class MFBitmapDescriptorFactory {
    public static MFBitmapDescriptor fromBitmap(Bitmap bitmap) {...}
    public static MFBitmapDescriptor fromView(View view) {...}
    public static MFBitmapDescriptor fromResource(@DrawableRes int resourceId) {...}
    public static MFBitmapDescriptor defaultMarker() {...}
}
```

<!-- tabs:start -->
#### ** Kotlin **

```kotlin
     val marker = map4D.addMarker(MFMarkerOptions()
                    .position(MFLocationCoordinate(10.771666, 106.704405))
                    .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.ic_add_location_white_48d)))
```

#### ** Java **

```java
     MFMarker marker = map4D.addMarker(new MFMarkerOptions()
                    .position(new MFLocationCoordinate(10.771666, 106.704405))
                    .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.ic_add_location_white_48dp)));
```
<!-- tabs:end -->

#### Hiển thị thông tin của Marker bằng infoWindow

- Khi marker có tiêu đề hoặc mô tả (title & snippet), nếu người dùng click vào marker, thông tin marker sẽ được hiển thị dựa vào điểm neo *windowAnchorU* và *windowAnchorV*.

<!-- tabs:start -->
#### ** Kotlin **

```kotlin
map4D.addMarker(MFMarkerOptions()
    .position(MFLocationCoordinate(10.771666, 106.704405))
    .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.markerIcon))
    .title("Title")
    .snippet("Snippet"))
```

### ** Java **

```java
map4D.addMarker(new MFMarkerOptions()
    .position(new MFLocationCoordinate(10.771666, 106.704405))
    .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.markerIcon))
    .title("Title")
    .snippet("Snippet"));
```
<!-- tabs:end -->

   > Chúng ta có thể tùy biến bảng hiển thị thông tin bao gồm cả layout lẫn nội dung
   
```java
public interface InfoWindowAdapter {
    android.view.View getInfoWindow(MFMarker marker);
    android.view.View getInfoContents(MFMarker marker);
}
```

- Hàm *getInfoWindow*: View của người dùng custom layout và nội dung, đc dùng khi View trả về != null
- Hàm *getInfoContents*: Custom nội dung, layout mặc định và được dùng khi getInfoWindow = null và View trả về != null

- Ví dụ

<!-- tabs:start -->

### ** Kotlin **

```kotlin
 inner class CustomInfoWindowAdapter: Map4D.InfoWindowAdapter {
    private val mWindow: View

    init {
      mWindow = layoutInflater.inflate(R.layout.custom_info_window, null)
    }

    override fun getInfoWindow(marker: MFMarker): View? {
      if (defaultInfoWindow) {
        return null
      }
      render(marker, mWindow)
      return mWindow
    }

    override fun getInfoContents(marker: MFMarker): View? {
      return null;
    }

    private fun render(marker: MFMarker, view: View) {
      val title = marker.title
      val titleUi = view.findViewById(R.id.title) as? TextView
      if (title != null) {
        // Spannable string allows us to edit the formatting of the text.
        val titleText = SpannableString(title)
        titleText.setSpan(ForegroundColorSpan(Color.RED), 0, titleText.length, 0)
        titleUi?.text = titleText
      } else {
        titleUi?.text = "Title"
      }

      val snippet = marker.snippet
      val snippetUi = view.findViewById(R.id.snippet) as? TextView
      if (snippet != null && snippet.length > 12) {
        val snippetText = SpannableString(snippet)
        snippetText.setSpan(ForegroundColorSpan(Color.MAGENTA), 0, 10, 0)
        snippetText.setSpan(ForegroundColorSpan(Color.BLUE), 12, snippet.length, 0)
        snippetUi?.text = snippetText
      } else {
        snippetUi?.text = snippet
      }
    }
 }
  
 override fun onMapReady(map4D: Map4D) {
    this.map4D = map4D
    map4D.setInfoWindowAdapter(CustomInfoWindowAdapter())
 }
```

### ** Java **

```java
class CustomInfoWindowAdapter implements Map4D.InfoWindowAdapter {
    private final View mWindow;

    CustomInfoWindowAdapter() {
        mWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null); 
    }

    @Override
    public View getInfoWindow(MFMarker marker) {
        if (defaultInfoWindow) {
            return null; 
        }
        render(marker, mWindow);
        return mWindow; 
    }

    @Override
    public View getInfoContents(MFMarker marker) {
        return null; 
    }

    private void render(MFMarker marker, View view) {
        String title = marker.getTitle();
        TextView titleUi = ((TextView) view.findViewById(R.id.title));
        if (title != null) {
            // Spannable string allows us to edit the formatting of the text.
            SpannableString titleText = new SpannableString(title);
            titleText.setSpan(new ForegroundColorSpan(Color.RED), 0, titleText.length(), 0);
            titleUi.setText(titleText); 
        } else {
            titleUi.setText(title); 
        }

        String snippet = marker.getSnippet();
        TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
        if (snippet != null && snippet.length() > 12) {
            SpannableString snippetText = new SpannableString(snippet);
            snippetText.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 10, 0);
            snippetText.setSpan(new ForegroundColorSpan(Color.BLUE), 12, snippet.length(), 0);
            snippetUi.setText(snippetText); 
        } else {
            snippetUi.setText(snippet); 
        } 
    }
    
}
@Override

public void onMapReady(Map4D map4D) {
    this.map4D = map4D;
    map4D.setInfoWindowAdapter(new CustomInfoWindowAdapter());
}
```
<!-- tabs:end -->

![Marker](https://raw.githubusercontent.com/iotlinkadmin/map4d-android-sdk/master/docs/resource/3-marker.png)

### 4. Tạo một Marker có thể kéo được trên bản đồ

> Để cho phép người dùng có thể kéo một Marker tới một vị trí khác trên bản đồ, chỉ định thuộc tính **draggable** thành **true** ở trong
**MFMarkerOptions**

> Ngoài ra bạn có thể gọi phương thức **setDraggable()** của đối tượng **Marker** và truyền vào tham số **true** để bật
  tính năng draggable của marker hoặc truyền vào tham số **false** để tắt tính năng draggable.
  
<!-- tabs:start -->

#### ** Kotlin  **
```kotlin
map4D.addMarker(MFMarkerOptions()
    .position(MFLocationCoordinate(10.771666, 106.704405))
    .draggable(true))  
or  

marker.isDraggable = true
```

#### ** Java **
```java
MFMarker marker = map4D.addMarker(new MFMarkerOptions()
                    .position(new MFLocationCoordinate(10.771666, 106.704405))
                    .draggable(true));  
or

marker.setDraggable(true);
```

<!-- tabs:end -->

### 5. Sự kiện click marker

Phát sinh khi người dùng click vào marker

<!-- tabs:start -->
#### ** Kotlin **
```kotlin
map4D?.setOnMarkerClickListener { marker ->
    Toast.makeText(context, "${marker.title}", Toast.LENGTH_SHORT).show()
    return@setOnMarkerClickListener false
}
```

#### ** Java **
```java
map4D.setOnMarkerClickListener(new Map4D.OnMarkerClickListener() {
    @Override
    public boolean onMarkerClick(MFMarker marker) {
        Toast.makeText(context, "Marker title: " + marker.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }
});
```
<!-- tabs:end -->

- Tham số *marker* sẽ trả về đối tượng marker mà người dùng click vào.
- Nếu trả về *true* thì sự kiện mặc định khi nhấn vào marker sẽ không được thực thi.
- Nếu trả về *false* thì sự kiện mặc định khi nhấn vào marker sẽ vẫn được thực thi.
- Sự kiện mặc định khi nhấn vào marker sẽ là hiển thị InfoWindow của marker.

### 6. Một vài lưu ý khi vẽ Marker
> Giá trị default zIndex của marker nếu người dùng không truyền vào là 1.f

> zIndex: Marker nào có zIndex lớn hơn sẽ được vẽ sau, zIndex càng lớn càng sẽ được vẽ sau.

Ví dụ: 

<!-- tabs:start -->

#### ** Kotlin **
```kotlin
    val markerA = map4D.addMarker(MFMarkerOptions()
                      .position(MFLocationCoordinate(10.771666, 106.704405))
                      .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.ic_default_marker))
                      .zIndex(5.f))

    val markerB = map4D.addMarker(MFMarkerOptions()
                        .position(MFLocationCoordinate(10.771666, 106.704405))
                        .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.ic_my_position))
                        .zIndex(2.f))
```

#### ** Java **
```java
    MFMarker markerA = map4D.addMarker(new MFMarkerOptions()
                      .position(new MFLocationCoordinate(10.771666, 106.704405))
                      .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.ic_default_marker))
                      .zIndex(5.f));

    MFMarker markerB = map4D.addMarker(new MFMarkerOptions()
                        .position(new MFLocationCoordinate(10.771666, 106.704405))
                        .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.ic_my_position))
                        .zIndex(2.f));
```
<!-- tabs:end -->

- Như ví dụ trên thì markerA sẽ hiển thị phía trước marker vì nó có zIndex lớn hơn markerB.

- zIndex bằng nhau thì thêm vào sau sẽ vẽ sau.

Ví dụ:
<!-- tabs:start -->
#### ** Kotlin **
```kotlin

    val markerA = map4D.addMarker(MFMarkerOptions()
                          .position(MFLocationCoordinate(10.771666, 106.704405))
                          .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.ic_default_marker)))

    val markerB = map4D.addMarker(MFMarkerOptions()
                          .position(MFLocationCoordinate(10.771666, 106.704405))
                          .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.ic_my_position)))

```

#### ** Java **
```java

    MFMarker markerA = map4D.addMarker(new MFMarkerOptions()
                          .position(new MFLocationCoordinate(10.771666, 106.704405))
                          .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.ic_default_marker)));

    MFMarker markerB = map4D.addMarker(new MFMarkerOptions()
                          .position(new MFLocationCoordinate(10.771666, 106.704405))
                          .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.ic_my_position)));

```
<!-- tabs:end -->
- Như ví dụ trên thì markerB sẽ hiển thị phía trước markerA vì cả 2 marker có cùng zIndex nhưng markerB lại thêm vào sau markerA.

------------------------------