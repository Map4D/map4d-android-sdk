# Marker
Lớp Marker cho phép người dùng add một điểm ghim trên bản đồ tại một vị trí xác định.

## 1. Marker & MarkerOptions

```java

public final class MFMarkerOptions {

    private LatLng position; // vị trí của marker

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
}

public final class MFMarker extends Annotation {

    private LatLng position; 

    private MFBitmapDescriptor icon;

    private View iconView;

    private double anchorU;

    private double anchorV;

    private boolean visible;

    private double elevation;

    private float zIndex;

    private float windowAnchorU;

    private float windowAnchorV;

    private String title;

    private String snippet;

    public MFMarker(@NonNull MFMarkerOptions markerOptions, @NonNull AnnotationDelegate annotationDelegate);

    public LatLng getPosition(); // 

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

    public void setPosition(@NonNull final LatLng position); //position của marker on Map

    public void setSnippet(String snippet); //set snippet cho Marker

    public void setTitle(String title); //set Tittle cho marker

    public void setWindowAnchor(float windowAnchorU, float windowAnchorV); // infoWindow Anchor

    public void setVisible(final boolean visible)// show/hide marker

    public void setZIndex(final float zIndex); // hiển thị theo thứ tự index, index càng cao thì vẽ trước

    public void setIconView(@NonNull final View iconView); // set custom View Marker

    public void showInfoWindow(); // hiển thị InfoWindow

    public void remove(); // xóa marker từ map
}

```
***Chú ý:***
  - nếu ko truyền thì icon sẽ dùng icon mặc định.
  - cung cấp file icon theo pixelscale của màn hình.
  - iconView: đây là custom view layout in android

## 2. Tạo marker

   Tạo marker từ markerOptions

```java
  View createMarkerView() {
        // Create new LinearLayout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(0x0FFFFFF);

        TextView textView1 = new TextView(this);
        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView1.setText("TextView");
        textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
        textView1.setPadding(20, 0, 20, 20); // in pixels (left, top, right, bottom)
        linearLayout.addView(textView1);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_default_marker);
        linearLayout.addView(imageView);
        return linearLayout;
    }

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
   
```

## 3. Sự kiện click marker

Phát sinh khi người dùng click vào marker

```java
    @Override
	public void onMapReady(Map4D map4D) {
	    this.map4D = map4D;
	    map4D.moveCamera(MFCameraUpdateFactory.newLatLngZoom(new LatLng(12.8006f, 106.784f), 6));
	    addMarkersToMap();
	    map4D.setInfoWindowAdapter(new CustomInfoWindowAdapter());
	    map4D.setOnMarkerClickListener(new Map4D.OnMarkerClickListener() {
	        @Override
	        public boolean onMarkerClick(MFMarker mfMarker) {
	            OnMarkerClick(mfMarker);
	            return false;
	        }
	    });
	}
	
	private void OnMarkerClick(MFMarker mfMarker) {
	    lastSelectedMarker = mfMarker;
	    Toast.makeText(getApplicationContext(), "Maker Id: " + mfMarker.getId(), Toast.LENGTH_SHORT).show();
	}
```

## 4. hiển thị thông tin marker (infoWindow)

- Khi marker có tiêu đề hoặc mô tả (title & snippet), nếu người dùng click vào marker, thông tin marker sẽ được hiển thị (dựa vào điểm neo windowAnchor)

- Tùy biến bảng hiển thị thông tin bao gồm cả layout or nội dung
```java
    public interface InfoWindowAdapter {
        android.view.View getInfoWindow(MFMarker marker);

        android.view.View getInfoContents(MFMarker marker);
    }
```

  - getInfoWindow: lấy View của người dùng custom layout và nội dung, đc dùng khi View trả về != null
  - getInfoContents: custom nội dung, layout mặc định và được dùng khi getInfoWindow = null và View trả về != null

- Example custom InfoWindow: 

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

License
-------

Copyright (C) 2016 IOT Link Ltd. All Rights Reserved.
