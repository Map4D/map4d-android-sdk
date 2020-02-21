# Polyline
Lớp Polyline cho phép người dùng vẽ một polyline lên map.

## 1. Polyline & PolylineOptions
```java
public final class MFPolylineOptions {
    private List<LatLng> points; //Một mảng danh sách các toạ độ cần vẽ polyline lên map.
    private String color; // Màu sắc
    private float alpha; // Độ trong suốt
    private float width; // Chiều rộng polyline
    private boolean visible; // Ẩn hoặc hiện polyline
    private boolean closed; // Cho phép nối điểm đầu và điểm cuối lại với nhau.
    private MFPolylineStyle style; // hỗ trợ 2 loại là nét đứt hoặc liền. dotted và solid. default là solid
    private float zIndex;  // thứ tự vẽ của polyline. default = -1.f
    public MFPolylineOptions();
    
    public MFPolylineOptions add(LatLng... points); // add một list points
    public MFPolylineOptions add(LatLng point); // add 1 point
    public MFPolylineOptions color(String color); // set color của polyline
    public MFPolylineOptions alpha(float alpha); // set độ trong suốt
    public MFPolylineOptions width(float width); //set width của polyline
    public MFPolylineOptions visible(boolean visible); // ẩn/hiện polyline
    public MFPolylineOptions closed(boolean closed); // cho điểm đầu nối điểm cuối 
    public MFPolylineOptions style(MFPolylineStyle style); // kiểu đường nét đứt hoặc nét liền
    public MFPolylineOptions zIndex(float zIndex); // thứ tự vẽ
    public List<LatLng> getPoints(); // lấy danh sách điểm tọa độ cần vẽ của polyline
    public String getColor(); // lấy màu sắc của polyline
    public float getAlpha(); // lấy độ trong suốt của polyline
    public float getWidth(); // lấy độ rộng của polyline;
    public boolean isVisible(); // kiểm tra polyline đang ẩn or hiện
    public boolean isClosed(); // kiểm tra polyline có cho nối điểm đầu với điểm cuối
    public MFPolylineStyle getStyle(); // kiểm tra kiểu nét vẽ của polyline
    public float getZIndex(); // kiểm tra giá trị của zIndex
}

public final class MFPolyline extends Annotation {
    public MFPolyline(@NonNull MFPolylineOptions polylineOptions,
                      @NonNull AnnotationDelegate annotationDelegate); // khởi tạp polyline từ MFPolylineOptions
    public List<LatLng> getPoints(); // lấy danh sách điểm tọa độ cần vẽ của polyline
    public String getColor(); // lấy màu sắc
    public float getAlpha(); // lấy độ trong suốt
    public float getWidth(); // lấy độ rộng
    public boolean isVisible(); // kiểm tra polyline đang ẩn or hiện
    public boolean isClosed(); // kiểm tra polyline có cho điểm đầu nối điểm cuối ko
    public void setWidth(float width); // set độ rộng
    public void setColor(@NonNull String color); // set màu sắc
    public void setAlpha(float alpha); // set độ trong suốt
    public void setVisible(boolean visible); // cho phép ẩn/hiện
    public void setPath(List<LatLng> path); // Set path cho Polyline
    public void remove(); // Remove polyline ra khoi map
}
```

## 2. Tạo polyline

![CocoaPods](https://raw.githubusercontent.com/iotlinkadmin/map4d-android-sdk/master/docs/resource/4-polyline.png)

- Tạo đối tượng polyline từ PolylineOptions

```java
  private final List<LatLng> latLngList = new ArrayList<>();

  latLngList.add(new LatLng(16.067218, 108.213916));
  latLngList.add(new LatLng(16.066496, 108.210311));
  latLngList.add(new LatLng(16.064877, 108.210397));
  latLngList.add(new LatLng(16.059980, 108.211137));
  latLngList.add(new LatLng(16.059516, 108.208358));

  polyline = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new LatLng[latLngList.size()]))
                .color("#0000ff")
                .width(8)
                .closed(false)
                .alpha(0.3f));
```
Ví dụ trên thì chúng ta tạo một polyline từ danh sách các tọa độ `latLngList` với các tùy chỉnh:
* Màu sắc: 0000ff
* Độ rộng của polyline: 8 point
* Polyline đóng: false
* Giá trị alpha của polyline: 0.3


- Tạo Polyline nét đứt. Mặc định style là nét liền.

```java
  final List<LatLng> latLngList = new ArrayList<>();

  latLngList.add(new LatLng(16.067218, 108.213916));
  latLngList.add(new LatLng(16.066496, 108.210311));
  latLngList.add(new LatLng(16.064877, 108.210397));
  latLngList.add(new LatLng(16.059980, 108.211137));
  latLngList.add(new LatLng(16.059516, 108.208358));

  polyline = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new LatLng[latLngList.size()]))
                .color("#0000ff")
                .width(8)
                .closed(false)
                .alpha(0.3f)
                .style(MFPolylineStyle.dotted));
```

![CocoaPods](https://raw.githubusercontent.com/map4d/map4d-android-sdk/master/docs/resource/4-polyline-dotted.jpg)
## 3. Sự kiện click polyline

Phát sinh khi người dùng click vào polyline

```java
  map4D.setOnPolylineClickListener(new Map4D.OnPolylineClickListener() {
    @Override
    public void onPolylineClick(MFPolyline polyline) {
        Toast.makeText(getApplicationContext(), "clicked Polyline: id " + polyline.getId(), Toast.LENGTH_SHORT).show();
    }
  });
```

* Tham số polyline sẽ trả về đối tượng polyline mà người dùng click vào

## 4. Xóa Polyline

Để xóa polyline ra khỏi bản đồ ta sử dụng hàm `remove()`

```java
  polyline.remove()
```

## 5. Thứ tự vẽ các layer

- Giá trị default zIndex của Polyline nếu người dùng không truyền vào là -1.f
- zIndex: Polyline nào có zIndex lớn hơn sẽ ưu tiên hiển thị trước, zIndex càng lớn càng sẽ được vẽ sau.

```java
	final List<LatLng> latLngList = new ArrayList<>();
    latLngList.add(new LatLng(16.067218, 108.213916));
    latLngList.add(new LatLng(16.066496, 108.210311));
    latLngList.add(new LatLng(16.064877, 108.210397));
    latLngList.add(new LatLng(16.059980, 108.211137));
    latLngList.add(new LatLng(16.059516, 108.208358));
    
    MFPolylineA polylineA = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new LatLng[latLngList.size()]))
                    .color("#0000ff")
                    .width(8)
                    .zIndex(5.f)
                    .alpha(1.f));
                    
    MFPolyline polylineB = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new LatLng[latLngList.size()]))
                    .color("#0000ff")
                    .width(8)
                    .zindex(10.f)
                    .alpha(1.f));
```
Như ví dụ ở trên thì polylineB sẽ đè lên polylineA vì nó có zIndex lớn hơn zIndex của polylineA.

- zIndex bằng nhau thì add vô sau sẽ vẽ sau.

**Ví dụ:**

```java
	final List<LatLng> latLngList = new ArrayList<>();
    latLngList.add(new LatLng(16.067218, 108.213916));
    latLngList.add(new LatLng(16.066496, 108.210311));
    latLngList.add(new LatLng(16.064877, 108.210397));
    latLngList.add(new LatLng(16.059980, 108.211137));
    latLngList.add(new LatLng(16.059516, 108.208358));
    
    MFPolylineA polylineA = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new LatLng[latLngList.size()]))
                    .color("#0000ff")
                    .width(8)
                    .zIndex(5.f)
                    .alpha(1.f));
                    
    MFPolyline polylineB = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new LatLng[latLngList.size()]))
                    .color("#0000ff")
                    .width(8)
                    .zindex(5.f)
                    .alpha(1.f));
```

Như ví dụ ở trên thì polylineB sẽ đè lên polylineA vì nó có zIndex bằng nhau nên được add vào sau sẽ được vẽ sau.

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
