# LatLngBounds
Lớp LatLngBounds đại diện cho một hình chữ nhật trong toạ độ địa lý, được biểu diễn bởi toạ độ 2 cực đông bắc và tây nam.

## 1. LatLngBounds

```java
public class LatLngBounds implements Parcelable {

    private static final double MAX_LATITUDE = 85.051128779806604;

    private static final double MIN_LATITUDE = -85.051128779806604;

    private static final double MAX_LONGITUDE = 180.0;

    private static final double MIN_LONGITUDE = -180.0;

    private LatLng southwest; // điểm tây nam của Bounds

    private LatLng northeast; // điểm đông bắc của Bounds
 
    public LatLngBounds(LatLng southwest, LatLng northeast); // khởi tạo
    
    public LatLng getSouthwest(); // lấy điểm Tây Nam
   
    public LatLng getNortheast();  // lấy điểm Đông Bắc

    static LatLngBounds world();  // bounds cho world

    protected LatLngBounds(Parcel in)

    @Override
    public void writeToParcel(Parcel dest, int flags)

    @Override
    public int describeContents()

    public static final Creator<LatLngBounds> CREATOR = new Creator<LatLngBounds>();

    public static Builder builder(); // builder pattern to make bounds

    public LatLngBounds include(LatLng latLng); // add 1 điểm rồi tính lại bounds

    public boolean contains(final LatLng latLng); //  check latlng có nằm trong bounds
    
    static LatLngBounds fromLatLngs(final List<? extends LatLng> latLngs); // tạo bounds từ list LatLng

    public static final class Builder {

        private final List<LatLng> latLngList = new ArrayList<>();

        public LatLngBounds build();

        public Builder includes(List<LatLng> latLngs); // add 1 LatLng vào Bounds

        public Builder include(@NonNull LatLng latLng); // add 1 List LatLng vào bounds
    }
}
```

## 2. Tạo đối tượng LatLngBounds

```java
private LatLngBounds latLngBounds = null;

private void createLatLngBounds() {
	List<LatLng> pointsList = new ArrayList<>();
	pointsList.add(new LatLng(16.058227, 108.200483));
	pointsList.add(new LatLng(16.074311, 108.212628));
	pointsList.add(new LatLng(16.073115, 108.192587));
	latLngBounds = new LatLngBounds.Builder().includes(pointsList).build();
}
```

## 3. Hướng dẫn sử dụng fitBounds

```java
 public MFCameraPosition getCameraPositionForLatLngBounds(LatLngBounds latLngBounds,
                                                     int padding);
```

***Sử dụng:***

```java
  private final List<MFMarker> markersList = new ArrayList<>();

  private void addMarkersToMap() {
      int numMarkersInRainbow = 12;
      for (int i = 0; i < numMarkersInRainbow; i++) {
          MFMarker marker = map4D.addMarker(new MFMarkerOptions()
                  .position(new LatLng(
                          10 + 0.8 * Math.sin(i * Math.PI / (numMarkersInRainbow - 1)),
                          106 - 0.8 * Math.cos(i * Math.PI / (numMarkersInRainbow - 1))))
                  .title("Marker  " + i)
                  .snippet(String.format("%f", 10 + 0.8 * Math.sin(i * Math.PI / (numMarkersInRainbow - 1)))
                          + ", "
                          + String.format("%f",106 - 0.8 * Math.cos(i * Math.PI / (numMarkersInRainbow - 1)))));
          markersList.add(marker);
      }
      View view = createMarkerView();
      MFMarker markerView = map4D.addMarker(new MFMarkerOptions()
              .position(new LatLng(13.0006, 106.784))
              .title("Marker  13")
              .snippet(13.0006f + ", " + 106.784f)
              .iconView(view));
      markersList.add(markerView);
  }
  
  void fitBounds(){
    LatLngBounds.Builder builder = new LatLngBounds.Builder();
    for (MFMarker marker : markersList) {
      builder.include(marker.getPosition());
    }
    MFCameraPosition cameraPosition = map4D.getCameraPositionForLatLngBounds(builder.build(), 10);
    map4D.moveCamera(MFCameraUpdateFactory.newCameraPosition(cameraPosition));
  }
```


License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
