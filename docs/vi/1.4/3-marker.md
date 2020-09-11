# Marker

Lớp Marker cho phép người dùng add một điểm ghim trên bản đồ tại một vị trí xác định.

## 1. Marker & MarkerOptions

```java

public final class MFMarkerOptions {
    private MFLocationCoordinate position; // vị trí của marker
    private MFBitmapDescriptor icon; // icon của marker
    private View iconView; // custom view icon của maker
    private double anchorU; // anchorU của marker
    private double anchorV; // anchorV của marker
    private boolean visible; // ẩn/hiển thị marker
    private double elevation; // độ cao của marker theo mét
    private float zIndex; // độ ưu tiên vẽ marker
    private float windowAnchorU; // infoWindow anchorU
    private float windowAnchorV; // infoWindow anchorV
    private String title; // title của marker
    private String snippet; // snippet của marker
    private boolean touchable; // có thể click on marker hay không, giá trị true là có thể touch, false là không touch được, mặc định là true
    private boolean draggable; // có thể drag marker hay không, giá trị true là có thể drag, false là không drag được, mặc định là false
    private double rotation; //  góc quay của marker theo trục Oz, góc quay theo chiều kim đồng hồ
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

## 2. Tạo marker

![CocoaPods](https://raw.githubusercontent.com/map4d/map4d-android-sdk/master/docs/resource/3-marker.png)

```java
map4D.addMarker(new MFMarkerOptions().touchable(true).draggable(true)
    .position(new MFLocationCoordinate(10.771666, 106.704405)));
```

Như ví dụ trên thì chúng ta tạo một marker ở vị trí (10.771666, 106.704405) tính theo LatLng với icon là mặc định.
Nếu muốn truyền vào icon khác thì dùng như sau:

```java
map4D.addMarker(new MFMarkerOptions()
    .position(new MFLocationCoordinate(10.771666, 106.704405))
    .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.markerIcon)));
```

Như ví dụ trên chúng ta tạo marker ở vị trí (10.771666, 106.704405) tính theo LatLng với icon được cung cấp từ người dùng.
Chúng ta có thể tạo *MFBitmapDescriptor* thông qua *MFBitmapDescriptorFactory* class.

```java
public final class MFBitmapDescriptorFactory {
    public static MFBitmapDescriptor fromBitmap(Bitmap bitmap) {...}
    public static MFBitmapDescriptor fromView(View view) {...}
    public static MFBitmapDescriptor fromResource(@DrawableRes int resourceId) {...}
    public static MFBitmapDescriptor defaultMarker() {...}
}
```

## 3. Tạo marker với icon view

```java
MFMarker markerView = map4D.addMarker(new MFMarkerOptions()
            .position(new MFLocationCoordinate(13.0006, 106.784))
            .iconView(view));
```

## 4. Xóa marker

Để xóa marker khỏi bản đồ ta sử dụng hàm *remove* 

```java
MFMarker marker = map4D.addMarker(new MFMarkerOptions().position(new MFLocationCoordinate(10.771666, 106.704405)));
marker.remove();
```

## 5. Sự kiện click marker

Phát sinh khi người dùng click vào marker

```java
map4D.setOnMarkerClickListener(new Map4D.OnMarkerClickListener() {
    @Override
    public boolean onMarkerClick(MFMarker marker) {
        return false;
    }
});
```
- Tham số *marker* sẽ trả về đối tượng marker mà người dùng click vào.
- Nếu trả về *true* thì sự kiện mặc định khi nhấn vào marker sẽ không được thực thi.
- Nếu trả về *false* thì sự kiện mặc định khi nhấn vào marker sẽ được thực thi.
- Sự kiện mặc định khi nhấn vào marker sẽ là hiện thị InfoWindow của marker.

## 6. Sự kiện drag Marker

Phát sinh khi người dùng drag marker

```java
map4D.setOnMarkerDragListener((new Map4D.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(MFMarker marker) {
                Log.i(Tag, "MFMarker | On Drag - id: " + marker.getId() + ", Pos: [" + marker.getPosition().getLatitude() + ", " + marker.getPosition().getLongitude() + "]" );
            }

            @Override
            public void onMarkerDragEnd(MFMarker marker) {
                Log.i(Tag, "MFMarker | Drag End - id: " + marker.getId() + ", Pos: [" + marker.getPosition().getLatitude() + ", " + marker.getPosition().getLongitude() + "]" );
            }

            @Override
            public void onMarkerDragStart(MFMarker marker) {
                Log.i(Tag, "MFMarker | Drag Start - id: " + marker.getId() + ", Pos: [" + marker.getPosition().getLatitude() + ", " + marker.getPosition().getLongitude() + "]" );
            }
        }));
```
- Tham số *marker* sẽ trả về đối tượng marker mà người dùng drag.

## 7. Hiển thị bảng thông tin marker 

- Khi marker có tiêu đề hoặc mô tả (title & snippet), nếu người dùng click vào marker, thông tin marker sẽ được hiển thị dựa vào điểm neo *windowAnchorU* và *windowAnchorV*.
```java
map4D.addMarker(new MFMarkerOptions()
    .position(new MFLocationCoordinate(10.771666, 106.704405))
    .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.markerIcon))
    .title("Title")
    .snippet("Snippet"));
```

Chúng ta có thể tùy biến bảng hiển thị thông tin bao gồm cả layout lẫn nội dung

```java
public interface InfoWindowAdapter {
    android.view.View getInfoWindow(MFMarker marker);
    android.view.View getInfoContents(MFMarker marker);
}
```
- Hàm *getInfoWindow* lấy View của người dùng custom layout và nội dung, đc dùng khi View trả về != null
- Hàm *getInfoContents* custom nội dung, layout mặc định và được dùng khi getInfoWindow = null và View trả về != null

- Ví dụ
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
    

	@Override
    public void onMapReady(Map4D map4D) {
        this.map4D = map4D;
        map4D.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }
}
...
map4D.setInfoWindowAdapter(new CustomInfoWindowAdapter());
```

## 8. Một vài lưu ý khi vẽ Marker
- Giá trị default zIndex của marker nếu người dùng không truyền vào là 1.f
- zIndex: Marker nào có zIndex lớn hơn sẽ ưu tiên hiển thị trước, zIndex càng lớn càng sẽ được vẽ sau.

Ví dụ: 
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
- Như ví dụ trên thì markerA sẽ hiển thị phía trước marker vì nó có zIndex lớn hơn markerB.

- zIndex bằng nhau thì add vô sau sẽ vẽ sau.

Ví dụ:
```java

    MFMarker markerA = map4D.addMarker(new MFMarkerOptions()
                          .position(new MFLocationCoordinate(10.771666, 106.704405))
                          .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.ic_default_marker)));

    MFMarker markerB = map4D.addMarker(new MFMarkerOptions()
                          .position(new MFLocationCoordinate(10.771666, 106.704405))
                          .icon(MFBitmapDescriptorFactory.fromResource(R.drawable.ic_my_position)));

```
- Như ví dụ trên thì markerB sẽ hiển thị phía trước markerA vì cả 2 marker có cùng zIndex nhưng markerB lại add vô sau markerA.

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
