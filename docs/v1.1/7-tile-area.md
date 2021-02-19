# TileArea
Lớp TileArea cho phép người dùng thực hiện thay thế các tile tại một khu vực nào đó bằng các tile khác.

## 1. TileArea & TileAreaOptions

```java
public class MFTileAreaOptions {

    private LatLngBounds bounds; // LatLng bounds để cho biết khu vực cần thay thế tile area

    private String mapUrl; // Đường dẫn tile mới

    private double minZoom; // Mức zoom tối thiểu của tile area

    private double maxZoom; // Mức zoom tối đa của tile area

    public MFTileAreaOptions(); // khởi tạo

    public MFTileAreaOptions bounds(@NonNull LatLngBounds bounds); // set bounds

    public MFTileAreaOptions mapUrl(@NonNull String mapUrl); // set url

    public MFTileAreaOptions minZoom(double minZoom); // set minzoom để hiện thị tile mới

    public MFTileAreaOptions maxZoom(double maxZoom); // set maxZoom để hiện thị tile mới

    public LatLngBounds getBounds(); // lấy bounds tile area

    public String getMapUrl(); // lấy map url

    public double getMinZoom(); // lấy minzoom của tile area

    public double getMaxZoom(); // lấy maxzoom của tile area
}

public class MFTileArea {

    private long id; // id : được tạo ra từ sdk

    private LatLngBounds bounds; // LatLng bounds để cho biết khu vực cần thay thế tile area

    private String mapUrl;  // Đường dẫn tile mới

    private double minZoom; // Mức zoom tối thiểu của tile area

    private double maxZoom; // Mức zoom tối đa của tile area

    private TileAreaDelegate tileAreaDelegate;

    public MFTileArea(@NonNull MFTileAreaOptions tileAreaOptions,
                      @NonNull TileAreaDelegate tileAreaDelegate); // khởi tạo

    public LatLngBounds getBounds(); // lấy bounds

    public String getMapUrl(); // lấy map url

    public double getMinZoom(); // lấy min zoom

    public double getMaxZoom();  // lấy max zoom

    public void setMapUrl(@NonNull final String mapUrl); // set map url

    public void setBounds(@NonNull final LatLngBounds bounds); // set bounds

    public void remove(); // remove tile area khỏi map

    void setId(long id);

    long getId();
}
```
## 2. Tạo tileArea

- Tạo tileAreaOptions

```java
  private MFTileArea tileArea;
  private LatLngBounds latLngBounds = null;

  private void createLatLngBounds() {
    List<LatLng> pointsList = new ArrayList<>();
    pointsList.add(new LatLng(16.058227, 108.200483));
    pointsList.add(new LatLng(16.074311, 108.212628));
    pointsList.add(new LatLng(16.073115, 108.192587));
    latLngBounds = new LatLngBounds.Builder().includes(pointsList).build();
  }

  private void addTileAreaToMap() {
    createLatLngBounds();
    if (map4D != null && latLngBounds != null && tileArea == null) {
        tileArea = map4D.addTileArea(new MFTileAreaOptions()
                .bounds(latLngBounds)
                .mapUrl("http://a.tile.openstreetmap.org/{z}/{x}/{y}.png")
                .minZoom(5.)
                .maxZoom(16.));
    }
  }
```

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
