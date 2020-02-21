# Circle
Lớp Circle cho phép người dùng vẽ một Circle lên map.

## 1. Circle & CircleOptions

```java
public class MFCircleOptions {
	private MFLocationCoordinate center; // Tâm của Circle 
	private double radius; // Bán kính Circle (Đơn vị: m)
	private String fillColor; // Màu sắc Circle
	private float fillAlpha; // Độ trong suốt Circle
	private boolean visible; // Ẩn hoặc hiện Circle
	private float zIndex; // thứ tự vẽ circle
	
	public MFCircleOptions();
	public MFCircleOptions center(MFLocationCoordinate center); // set vị trí tâm
	public MFCircleOptions radius(double radius); // set bán kính
	public MFCircleOptions fillColor(String color); // set màu sắc
	public MFCircleOptions fillAlpha(float alpha); // set độ trong suốt
	public MFCircleOptions visible(boolean visible); // set ẩn hoặc hiện circle
	public MFCircleOptions zIndex(float zIndex); // cài đặt thứ tự vẽ circle trên map
	public MFLocationCoordinate getCenter();  // lấy vị trí tâm
	public double getRadius(); // lấy bán kính
	public String getFillColor(); // lấy màu sắc
	public float getFillAlpha(); // lấy độ trong suốt;
	public boolean isVisible(); // kiểm tra ẩn hiện
	public float getZIndex(); // lấy giá trị của zIndex
}

public class MFCircle extends Annotation {
    public MFCircle(@NonNull MFCircleOptions circleOptions,
                    @NonNull AnnotationDelegate annotationDelegate);
    public MFLocationCoordinate getCenter(); // lấy điểm tâm
    public double getRadius(); // lấy bán kính
    public void setRadius(double radius); // set bán kính
    public void setCenter(MFLocationCoordinate center); // set vị trí tâm
    public String getFillColor(); // lấy color
    public float getFillAlpha(); // lấy độ trong suốt
    public boolean isVisible(); // kiểm tra circle đang ẩn hoặc hiện
    public void setFillColor(@NonNull String color); // set màu của circle
    public void setFillAlpha(float alpha); // set độ trong suốt
    public void setVisible(boolean visible); // ẩn hoặc hiện circle
    public void remove(); // remove circle khỏi map
}
```

## 2. Tạo circle

![CocoaPods](https://raw.githubusercontent.com/iotlinkadmin/map4d-android-sdk/master/docs/resource/6-circle.png)

- Tạo circle from MFCircleOptions 

```java
 MFCircle circle = map4D.addCircle(new MFCircleOptions()
                        .center(new MFLocationCoordinate(16.066517, 108.210354))
                        .radius(500)
                        .fillColor("#00ff00")
                        .fillAlpha(0.3f));
```

Như ví dụ trên thì chúng ta tạo một Circle với các tùy chỉnh như sau:
* Tâm của Circle ở tọa độ `LatLng` 16.066517, 108.210354
* Bán kính của Circle là: 500 mét
* Màu của Circle là: 00ff00
* Giá trị alpha của Circle là: 0.3

- Tạo circle với strokeColor and strokeWidth.

![CocoaPods](https://raw.githubusercontent.com/map4d/map4d-android-sdk/master/docs/resource/6-circle-stroke.jpg)

```java
 MFCircle circle = map4D.addCircle(new MFCircleOptions()
                        .center(new MFLocationCoordinate(16.066517, 108.210354))
                        .radius(300)
                        .fillColor("#ffaabb")
                        .fillAlpha(0.5f)
                        .strokeWidth(5.f)
                        .strokeColor("#00ff00"));
```

**Lưu ý:**

   - Stroke width đơn vị là point tương đương dp trong android
   - Stroke width mặc định là 0.f(không vẽ)      

## 3. Sự kiện click circle

Phát sinh khi người dùng click vào circle

```java
map4D.setOnCircleClickListener(new Map4D.OnCircleClickListener() {
    @Override
    public void onCircleClick(MFCircle mfCircle) {
        Toast.makeText(getApplicationContext(), "Circle clicked:  " + mfCircle.getId(), Toast.LENGTH_SHORT).show();
    }
});
```

* Tham số mfCircle sẽ trả về đối tượng Circle mà người dùng click vào

## 4. Xóa Circle

Để xóa Circle ra khỏi bản đồ ta sử dụng hàm `remove()`

```java
  circle.remove()
```

## 5. Thứ tự vẽ các layer

- Giá trị default zIndex của Circle nếu người dùng không truyền vào là -1.f
- zIndex: Circle nào có zIndex lớn hơn sẽ ưu tiên hiển thị trước, zIndex càng lớn càng sẽ được vẽ sau.

```java
  MFCircle circleA = map4D.addCircle(new MFCircleOptions()
                          .center(new MFLocationCoordinate(16.066517, 108.210354))
                          .radius(500)
                          .fillColor("#00ff00")
                          .zIndex(10.f));
  MFCircle circleB = map4D.addCircle(new MFCircleOptions()
                          .center(new MFLocationCoordinate(16.066517, 108.210354))
                          .radius(500)
                          .fillColor("#00ff00")
                          .zIndex(2.f));
```
- CircleA sẽ được vẽ đè lên vì zIndex của nó lớn hơn zIndex của circleB.

```java
  MFCircle circleA = map4D.addCircle(new MFCircleOptions()
                            .center(new MFLocationCoordinate(16.066517, 108.210354))
                            .radius(500)
                            .fillColor("#00ff00"));
  MFCircle circleB = map4D.addCircle(new MFCircleOptions()
                            .center(new MFLocationCoordinate(16.066517, 108.210354))
                            .radius(500)
                            .fillColor("#00ff00"));
```

- CircleB sẽ vẽ đè lên CircleA vì nó có zIndex bằng nhau. Cùng zIndex thì layer nào thêm vào sau sẽ vẽ đè lên layer trước.

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
