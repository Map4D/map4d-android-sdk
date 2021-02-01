# Polyline

Để vẽ đường thẳng trên bản đồ thì ta sử dụng đối tượng Polyline. Một đối tượng Polyline bao gồm một mảng các điểm tọa độ
và tạo ra các đoạn thẳng nối các vị trí đó theo một trình tự có thứ tự.

## 1. Polyline & PolylineOptions
```java
public final class MFPolylineOptions {
    private List<MFLocationCoordinate> points; //Một mảng danh sách các toạ độ cần vẽ polyline lên map.
    @ColorInt
    private int color; // Màu sắc
    private float width; // Chiều rộng polyline
    private boolean visible; // Ẩn hoặc hiện polyline
    private MFPolylineStyle style; // hỗ trợ 2 loại là nét đứt hoặc liền. dotted và solid. default là solid
    private float zIndex;  // thứ tự vẽ của polyline. default = -1.f
    private boolean touchable; // có thể click on Polyline hay không, giá trị true là có thể touch, false là không touch được, mặc định là true
    
    public MFPolylineOptions();
    public MFPolylineOptions add(MFLocationCoordinate... points); // add một list points
    public MFPolylineOptions add(MFLocationCoordinate point); // add 1 point
    public MFPolylineOptions color(@ColorInt int color); // set color của polyline
    public MFPolylineOptions width(float width); //set width của polyline
    public MFPolylineOptions visible(boolean visible); // ẩn/hiện polyline
    public MFPolylineOptions touchable(boolean touchable); // có thể touch polyline hay không
    public MFPolylineOptions style(MFPolylineStyle style); // kiểu đường nét đứt hoặc nét liền
    public MFPolylineOptions zIndex(float zIndex); // thứ tự vẽ
    public List<MFLocationCoordinate> getPoints(); // lấy danh sách điểm tọa độ cần vẽ của polyline
    @ColorInt
    public int getColor(); // lấy màu sắc của polyline
    public float getWidth(); // lấy độ rộng của polyline;
    public boolean isVisible(); // kiểm tra polyline đang ẩn or hiện
    public MFPolylineStyle getStyle(); // kiểm tra kiểu nét vẽ của polyline
    public float getZIndex(); // kiểm tra giá trị của zIndex
    public boolean getTouchable(); // kiểm tra giá trị của touchable
}

public final class MFPolyline extends Annotation {
    public MFPolyline(@NonNull MFPolylineOptions polylineOptions,
                      @NonNull AnnotationDelegate annotationDelegate); // khởi tạp polyline từ MFPolylineOptions
    public List<MFLocationCoordinate> getPoints(); // lấy danh sách điểm tọa độ cần vẽ của polyline
    public String getColor(); // lấy màu sắc
    public float getAlpha(); // lấy độ trong suốt
    public float getWidth(); // lấy độ rộng
    public boolean isVisible(); // kiểm tra polyline đang ẩn or hiện
    public void setWidth(float width); // set độ rộng
    public void setColor(@NonNull String color); // @Deprecate set màu sắc
    public void setColor(@ColorInt int color); // set màu sắc
    public void setAlpha(float alpha); // @Deprecate. dùng setFillColor(@ColorInt int color) set độ trong suốt
    public void setVisible(boolean visible); // cho phép ẩn/hiện
    public void setPath(List<MFLocationCoordinate> path); // Set path cho Polyline
    public void remove(); // Remove polyline ra khoi map
}

public enum MFPolylineStyle {
    Solid(0),
    Dotted(1);

    private int value;

    MFPolylineStyle(int value)
    {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
```

Các thuộc tính của **MFPolylineOptions** :

- **points** : truyền vào một list các tọa độ **MFLocationCoordinate** cần vẽ của Polyline.
- **color** : chỉ định màu sắc của Polyline theo kiểu **ColorInt**. Giá trị màu mặc định là Color.Black
- **width** : chỉ định độ rộng của Polyline theo đơn vị point (dp).
- **visible** : xác định Polyline có thể ẩn hay hiện trên bản đồ. Giá trị mặc định là **true**.
- **style** : Dùng kiểu **MFPolylineStyle** để chỉ định style của Polyline là loại nét liền (**"Solid"**) hay nét đứt (**"Dotted"**). Giá trị mặc định là **"Solid"**
- **zIndex** : chỉ định thứ tự hiển thị giữa các Polyline với nhau hoặc giữa Polyline với các đối tượng khác
trên bản đồ. Giá trị mặc định là **-1.0f**.
- **touchable** : cho phép người dùng có thể tương tác với circle hay không. Giá trị mặc định là **true**.

### 2. Tạo polyline

![Polyline](https://raw.githubusercontent.com/iotlinkadmin/map4d-android-sdk/master/docs/resource/4-polyline.png)

> Tạo đối tượng polyline từ **MFPolylineOptions**


<!-- tabs:start -->
#### ** Kotlin **
```kotlin

  val latLngList = mutableListOf<MFLocationCoordinate>()
  latLngList.add(MFLocationCoordinate(16.067218, 108.213916))
  latLngList.add(MFLocationCoordinate(16.066496, 108.210311))
  latLngList.add(MFLocationCoordinate(16.064877, 108.210397))
  latLngList.add(MFLocationCoordinate(16.059980, 108.211137))
  latLngList.add(MFLocationCoordinate(16.059516, 108.208358))
  latLngList.add(MFLocationCoordinate(16.067218, 108.213916))
  val polyline = map4D?.addPolyline(
    MFPolylineOptions().add(*latLngList.toTypedArray())
    .color(ContextCompat.getColor(context ?: return, R.color.red))
    .width(8.0f))
```

#### ** Java **
```java
  private final List<MFLocationCoordinate> latLngList = new ArrayList<>();

  latLngList.add(new MFLocationCoordinate(16.067218, 108.213916));
  latLngList.add(new MFLocationCoordinate(16.066496, 108.210311));
  latLngList.add(new MFLocationCoordinate(16.064877, 108.210397));
  latLngList.add(new MFLocationCoordinate(16.059980, 108.211137));
  latLngList.add(new MFLocationCoordinate(16.059516, 108.208358));
  latLngList.add(new MFLocationCoordinate(16.067218, 108.213916));

  polyline = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new MFLocationCoordinate[latLngList.size()]))
                .color(ContextCompat.getColor(this, R.color.red))
                .width(8);
```
<!-- tabs:end -->
Ví dụ trên thì chúng ta tạo một polyline từ danh sách các tọa độ `latLngList` với các tùy chỉnh:
* Màu sắc: #4D0000ff, giá trị alpha của color la 4D(hex), 0.3 float
* Độ rộng của polyline: 8 point

- Tạo Polyline nét đứt. Mặc định style là nét liền.

<!-- tabs:start -->
#### ** Kotlin **
```kotlin
  val latLngList: MutableList<MFLocationCoordinate> = mutableListOf()
  
      latLngList.add(MFLocationCoordinate(16.067218, 108.213916))
      latLngList.add(MFLocationCoordinate(16.066496, 108.210311))
      latLngList.add(MFLocationCoordinate(16.064877, 108.210397))
      latLngList.add(MFLocationCoordinate(16.059980, 108.211137))
      latLngList.add(MFLocationCoordinate(16.059516, 108.208358))
  
      val polyline = map4D!!.addPolyline(
        MFPolylineOptions().add(*latLngList.toTypedArray())
          .color(Color.RED)
          .width(8f)
          .style(MFPolylineStyle.Dotted)
```
#### ** Java **
```java
  final List<MFLocationCoordinate> latLngList = new ArrayList<>();

  latLngList.add(new MFLocationCoordinate(16.067218, 108.213916));
  latLngList.add(new MFLocationCoordinate(16.066496, 108.210311));
  latLngList.add(new MFLocationCoordinate(16.064877, 108.210397));
  latLngList.add(new MFLocationCoordinate(16.059980, 108.211137));
  latLngList.add(new MFLocationCoordinate(16.059516, 108.208358));

  polyline = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new MFLocationCoordinate[latLngList.size()]))
                .color(Color.RED)
                .width(8)
                .style(MFPolylineStyle.Dotted));
```
<!-- tabs:end -->

- **Result**: 

![Polyline](https://raw.githubusercontent.com/map4d/map4d-android-sdk/master/docs/resource/4-polyline-dotted.jpg)

### 3. Xóa Polyline

> Để xóa polyline ra khỏi bản đồ ta sử dụng hàm `remove()`

<!-- tabs:start -->
#### ** Kotlin **
```kotlin
  polyline.remove()
```
#### ** Java **
```java
  polyline.remove();
```
<!-- tabs:end -->

### 4. Sự kiện click polyline

- Phát sinh khi người dùng click vào polyline

<!-- tabs:start -->

#### ** Kotlin **
```kotlin
    map4D?.setOnPolylineClickListener{ polyline ->
        Toast.makeText( context ?: return@setOnPolylineClickListener,
          "clicked Polyline: id " + polyline.id,
          Toast.LENGTH_SHORT
        ).show()
    }
```
#### ** Java **
```java
  map4D.setOnPolylineClickListener(new Map4D.OnPolylineClickListener() {
    @Override
    public void onPolylineClick(MFPolyline polyline) {
        Toast.makeText(getApplicationContext(), "clicked Polyline: id " + polyline.getId(), Toast.LENGTH_SHORT).show();
    }
  });
```
<!-- tabs:end -->
* Tham số polyline sẽ trả về đối tượng polyline mà người dùng click vào

## 5. Thứ tự vẽ các layer

- Giá trị default zIndex của Polyline nếu người dùng không truyền vào là -1.f
- zIndex: Polyline nào có zIndex lớn hơn sẽ ưu tiên hiển thị trước, zIndex càng lớn càng sẽ được vẽ sau.

<!-- tabs:start -->
#### ** Kotlin **
```kotlin
	val latLngList: MutableList<MFLocationCoordinate> = ArrayList()
    latLngList.add(MFLocationCoordinate(16.067218, 108.213916))
    latLngList.add(MFLocationCoordinate(16.066496, 108.210311))
    latLngList.add(MFLocationCoordinate(16.064877, 108.210397))
    latLngList.add(MFLocationCoordinate(16.059980, 108.211137))
    latLngList.add(MFLocationCoordinate(16.059516, 108.208358))

    val polylineA = map4D!!.addPolyline(
      MFPolylineOptions().add(*latLngList.toTypedArray())
        .color(Color.BLUE)
        .width(20f)
        .zIndex(5f)
    )

    val polylineB = map4D!!.addPolyline(
      MFPolylineOptions().add(*latLngList.toTypedArray())
        .color(Color.RED)
        .width(8f)
        .zIndex(10f)
    )
```
#### ** Java **
```java
	final List<MFLocationCoordinate> latLngList = new ArrayList<>();
    latLngList.add(new MFLocationCoordinate(16.067218, 108.213916));
    latLngList.add(new MFLocationCoordinate(16.066496, 108.210311));
    latLngList.add(new MFLocationCoordinate(16.064877, 108.210397));
    latLngList.add(new MFLocationCoordinate(16.059980, 108.211137));
    latLngList.add(new MFLocationCoordinate(16.059516, 108.208358));
    
    MFPolylineA polylineA = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new MFLocationCoordinate[latLngList.size()]))
                    .color(ContextCompat.getColor(this, R.color.blue))
                    .width(20.f)
                    .zIndex(5.f));
                    
    MFPolyline polylineB = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new MFLocationCoordinate[latLngList.size()]))
                    .color(ContextCompat.getColor(this, R.color.blue))
                    .width(8.f)
                    .zindex(10.f));
```
<!-- tabs:end -->
Như ví dụ ở trên thì polylineB sẽ đè lên polylineA vì nó có zIndex lớn hơn zIndex của polylineA.

- zIndex bằng nhau thì add vào sau sẽ vẽ sau.

**Ví dụ:**

<!-- tabs:start -->
#### ** Kotlin **
```kotlin
val latLngList: MutableList<MFLocationCoordinate> = ArrayList()
    latLngList.add(MFLocationCoordinate(16.067218, 108.213916))
    latLngList.add(MFLocationCoordinate(16.066496, 108.210311))
    latLngList.add(MFLocationCoordinate(16.064877, 108.210397))
    latLngList.add(MFLocationCoordinate(16.059980, 108.211137))
    latLngList.add(MFLocationCoordinate(16.059516, 108.208358))

    val polylineA = map4D!!.addPolyline(
      MFPolylineOptions().add(*latLngList.toTypedArray())
        .color(Color.BLUE)
        .width(8f)
        .zIndex(5f)
    )

    val polylineB = map4D!!.addPolyline(
      MFPolylineOptions().add(*latLngList.toTypedArray())
        .color(Color.RED)
        .width(20f)
        .zIndex(5f)
    )
```

#### ** Java **
```java
	final List<MFLocationCoordinate> latLngList = new ArrayList<>();
    latLngList.add(new MFLocationCoordinate(16.067218, 108.213916));
    latLngList.add(new MFLocationCoordinate(16.066496, 108.210311));
    latLngList.add(new MFLocationCoordinate(16.064877, 108.210397));
    latLngList.add(new MFLocationCoordinate(16.059980, 108.211137));
    latLngList.add(new MFLocationCoordinate(16.059516, 108.208358));
    
    MFPolylineA polylineA = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new MFLocationCoordinate[latLngList.size()]))
                    .color(ContextCompat.getColor(this, R.color.blueWithAlpha))
                    .width(8.f)
                    .zIndex(5.f));
                    
    MFPolyline polylineB = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new MFLocationCoordinate[latLngList.size()]))
                    .color(ContextCompat.getColor(this, R.color.blueWithAlpha))
                    .width(20.f)
                    .zindex(5.f));
```
<!-- tabs:end -->

Như ví dụ ở trên thì polylineB sẽ đè lên polylineA vì nó có zIndex bằng nhau nên được add vào sau sẽ được vẽ sau.