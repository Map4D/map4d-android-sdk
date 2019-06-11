# Polygon
Lớp Polygon cho phép người dùng vẽ một Polygon lên map.

## 1. Polygon & PolygoneOptions

```java
public class MFPolygonOptions {
    private List<LatLng> points; // Danh sách các toạ độ cần vẽ
    private List<List<LatLng>> holes; // Danh sách các tọa độ holes
    private String fillColor;  // màu sắc
    private float fillAlpha;  // độ trong suốt
    private boolean visible;  // ẩn/hiển polygon
    
    public MFPolygonOptions(); // khởi tạo 
    public MFPolygonOptions add(LatLng... points); //add danh sách các tọa độ cần vẽ
    public MFPolygonOptions add(LatLng point); // add 1 point vào danh sách tọa độ
    public MFPolygonOptions addHole(LatLng... points); // add danh sách tọa độ holes
    public MFPolygonOptions fillColor(String color); // set màu sắc
    public MFPolygonOptions alpha(float alpha); // set độ trong suốt
    public MFPolygonOptions visible(boolean visible); // cho phép ẩn hiện
    public List<LatLng> getPoints(); // lấy danh sách tọa độ cần vẽ
    public List<List<LatLng>> getHoles(); // lấy danh sách tọa đọ holes
    public String getFillColor(); // lấy màu của polygon
    public float getFillAlpha(); // lấy độ trong suốt
    public boolean isVisible(); // kiểm tra ẩn hiện của polygon
}

public class MFPolygon extends Annotation {
    public MFPolygon(@NonNull MFPolygonOptions polygonOptions,
                     @NonNull AnnotationDelegate annotationDelegate); // khởi tạo
    public List<LatLng> getPoints();  // lấy danh sách tọa độ cần vẽ
    public List<List<LatLng>> getHoles(); // lấy danh sách tọa đọ holes
    public String getFillColor(); // lấy màu của polygon
    public float getFillAlpha(); // lấy độ trong suốt
    public boolean isVisible(); // kiểm tra ẩn hiện của polygon
    public void setFillColor(@NonNull String color); set màu sắc
    public void setFillAlpha(float alpha); // set độ trong suốt
    public void setVisible(boolean visible); // cho phép ẩn hiện
    public void setPoints(List<LatLng> points); //add danh sách các tọa độ cần vẽ
    public void setHoles(List<List<LatLng>> holes); // add danh sách tọa độ holes
    public void remove(); // remove polygon khỏi map
}
```

## 2. Tạo polygon

- Tạo đối tượng polygon từ PolygonOptions



```java
  private final List<LatLng> pointsList = new ArrayList<>();

  private void createPointsList() {
  	pointsList.add(new LatLng(16.066517, 108.210354));
  	pointsList.add(new LatLng(16.067243, 108.214077));
  	pointsList.add(new LatLng(16.065419, 108.214576));
  	pointsList.add(new LatLng(16.062815, 108.214034));
  	pointsList.add(new LatLng(16.062434, 108.210772));
  	pointsList.add(new LatLng(16.066517, 108.210354));
 }

  private void addPolygonToMap() {
	createPointsList();
	polygon = map4D.addPolygon(new MFPolygonOptions()
	        .add(pointsList.toArray(new LatLng[pointsList.size()]))
	        .fillColor("#0000ff")
	        .alpha(0.5f));
	}
```

Như ví dụ trên thì chúng ta tạo một Polygon từ danh sách các điểm tọa độ trong danh sách `pointsList` . Các tùy chỉnh gồm:

* Màu cho polygon: 0000ff
* Giá trị alpha của Polygon: 0.5

Ngoài ra chúng ta còn có thể tạo ra các Polygon có lỗ ở bên trong bằng cách thêm danh sách cái điểm tạo thành lỗ ở phía trong
polygon.

Ví dụ

```java
  private final List<LatLng> pointsList = new ArrayList<>();
  private final List<LatLng> holePath = new ArrayList<>();

  private void createPointsList() {
  	pointsList.add(new LatLng(16.066517, 108.210354));
  	pointsList.add(new LatLng(16.067243, 108.214077));
  	pointsList.add(new LatLng(16.065419, 108.214576));
  	pointsList.add(new LatLng(16.062815, 108.214034));
  	pointsList.add(new LatLng(16.062434, 108.210772));
  	pointsList.add(new LatLng(16.066517, 108.210354));
 }

  private void createHole() {
    holePath.add(new LatLng(16.065681, 108.211716));
    holePath.add(new LatLng(16.065898, 108.213009));
    holePath.add(new LatLng(16.065336, 108.213202));
    holePath.add(new LatLng(16.064965, 108.212183));
    holePath.add(new LatLng(16.065681, 108.211716));
  }

  private void addPolygonToMap() {
	createHole();
	createPointsList();
	polygon = map4D.addPolygon(new MFPolygonOptions()
	        .add(pointsList.toArray(new LatLng[pointsList.size()]))
	        .addHole(holePath.toArray(new LatLng[holePath.size()]))
	        .fillColor("#0000ff")
	        .alpha(0.5f));
	}
```

  ***Chú ý:***
  -  Điểm đầu điểm cuối danh sách các điểm cần vẽ phải giống nhau
  
  Xem demo ở ví dụ sau đây:
  
```java
    pointsList.add(new LatLng(16.066517, 108.210354));
  	pointsList.add(new LatLng(16.067243, 108.214077));
  	pointsList.add(new LatLng(16.065419, 108.214576));
  	pointsList.add(new LatLng(16.062815, 108.214034));
  	pointsList.add(new LatLng(16.062434, 108.210772));
  	pointsList.add(new LatLng(16.066517, 108.210354));
```

## 3. Sự kiện click polygon

Phát sinh khi người dùng click vào polygon

```java
map4D.setOnPolygonClickListener(new Map4D.OnPolygonClickListener() {
    @Override
    public void onPolygonClick(MFPolygon polygon) {
        Toast.makeText(getApplicationContext(), "Clicked Polygon: ID " + polygon.getId(), Toast.LENGTH_SHORT).show();
    }
})
```

* Tham số polygon sẽ trả về đối tượng polygon mà người dùng click vào

## 4. Xóa Polygon

Để xóa Polygon ra khỏi bản đồ ta sử dụng hàm `remove()`

```java
  polygon.remove()
```

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
