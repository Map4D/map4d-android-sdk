# MFCoordinateBounds
Lớp MFCoordinateBounds đại diện cho một hình chữ nhật trong toạ độ địa lý, được biểu diễn bởi toạ độ 2 cực đông bắc và tây nam.

## 1. MFCoordinateBounds

```java
public class MFCoordinateBounds implements Parcelable {

    private static final double MAX_LATITUDE = 85.051128779806604;

    private static final double MIN_LATITUDE = -85.051128779806604;

    private static final double MAX_LONGITUDE = 180.0;

    private static final double MIN_LONGITUDE = -180.0;

    private MFLocationCoordinate southwest; // điểm tây nam của Bounds

    private MFLocationCoordinate northeast; // điểm đông bắc của Bounds
 
    public MFCoordinateBounds(MFLocationCoordinate southwest, MFLocationCoordinate northeast); // khởi tạo
    
    public MFLocationCoordinate getSouthwest(); // lấy điểm Tây Nam
   
    public MFLocationCoordinate getNortheast();  // lấy điểm Đông Bắc

    static MFCoordinateBounds world();  // bounds cho world

    protected MFCoordinateBounds(Parcel in)

    @Override
    public void writeToParcel(Parcel dest, int flags)

    @Override
    public int describeContents()

    public static final Creator<MFCoordinateBounds> CREATOR = new Creator<MFCoordinateBounds>();

    public static Builder builder(); // builder pattern to make bounds

    public MFCoordinateBounds include(MFLocationCoordinate latLng); // add 1 điểm rồi tính lại bounds

    public boolean contains(final MFLocationCoordinate latLng); //  check latlng có nằm trong bounds
    
    static MFCoordinateBounds fromCoordinates(final List<? extends MFLocationCoordinate> latLngs); // tạo bounds từ list LatLng

    public static final class Builder {

        private final List<MFLocationCoordinate> latLngList = new ArrayList<>();

        public MFCoordinateBounds build();

        public Builder includes(List<MFLocationCoordinate> latLngs); // add 1 LatLng vào Bounds

        public Builder include(@NonNull MFLocationCoordinate latLng); // add 1 List LatLng vào bounds
    }
}
```

## 2. Tạo đối tượng MFCoordinateBounds

```java
private MFCoordinateBounds latLngBounds = null;

private void createLatLngBounds() {
	List<MFLocationCoordinate> pointsList = new ArrayList<>();
	pointsList.add(new MFLocationCoordinate(16.058227, 108.200483));
	pointsList.add(new MFLocationCoordinate(16.074311, 108.212628));
	pointsList.add(new MFLocationCoordinate(16.073115, 108.192587));
	latLngBounds = new MFCoordinateBounds.Builder().includes(pointsList).build();
}
```

## 3. Hướng dẫn sử dụng fitBounds

```java
 public MFCameraPosition getCameraPositionForBounds(MFCoordinateBounds latLngBounds,
                                                     int padding); // padding: point (dp)

 public MFCameraPosition getCameraPositionForBounds(MFCoordinateBounds latLngBounds,
                                                     int paddingLeft, int paddingTop, int paddingRight, int paddingBottom));
```

***Sử dụng:***

```java
  private final List<MFMarker> markersList = new ArrayList<>();

  private void addMarkersToMap() {
      int numMarkersInRainbow = 12;
      for (int i = 0; i < numMarkersInRainbow; i++) {
          MFMarker marker = map4D.addMarker(new MFMarkerOptions()
                  .position(new MFLocationCoordinate(
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
              .position(new MFLocationCoordinate(13.0006, 106.784))
              .title("Marker  13")
              .snippet(13.0006f + ", " + 106.784f)
              .iconView(view));
      markersList.add(markerView);
  }
  
  void fitBounds(){
    MFCoordinateBounds.Builder builder = new MFCoordinateBounds.Builder();
    for (MFMarker marker : markersList) {
      builder.include(marker.getPosition());
    }
    MFCameraPosition cameraPosition = map4D.getCameraPositionForBounds(builder.build(), 10);
	
    //MFCameraPosition cameraPosition = map4D.getCameraPositionForBounds(builder.build(), 100, 200, 0, 0);
    map4D.moveCamera(MFCameraUpdateFactory.newCameraPosition(cameraPosition));
  }
```


License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
