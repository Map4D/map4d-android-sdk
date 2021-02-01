package com.map4dsdk.demo;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.map4d.map.annotations.MFBuilding;
import vn.map4d.map.annotations.MFBuildingOptions;
import vn.map4d.map.annotations.MFMarker;
import vn.map4d.map.annotations.MFMarkerOptions;
import vn.map4d.map.annotations.MFPOI;
import vn.map4d.map.camera.MFCameraPosition;
import vn.map4d.map.camera.MFCameraUpdateFactory;
import vn.map4d.map.core.MFCoordinateBounds;
import vn.map4d.types.MFLocationCoordinate;
import vn.map4d.map.core.MFSupportMapFragment;
import vn.map4d.map.core.Map4D;
import vn.map4d.map.core.OnMapReadyCallback;

public class MarkerActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private Map4D map4D;
    private  boolean clearMarkers = false;
    private  boolean defaultInfoWindow = true;
    private final List<MFMarker> markersList = new ArrayList<>();
    private MFMarker lastSelectedMarker = null;

    class CustomInfoWindowAdapter implements Map4D.InfoWindowAdapter {

        // These are both viewgroups containing an ImageView with id "badge" and two TextViews with id
        // "title" and "snippet".
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marker_activity);
        MFSupportMapFragment mapFragment = (MFSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.markerDemo);
        mapFragment.getMapAsync(this);
        onListener();
        getSupportActionBar().setTitle(R.string.marker);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

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
                    .touchable(true)
                    .draggable(true)
                    .rotation(90.)
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

    private void removeMakersFromMap() {
        for (MFMarker marker : markersList) {
            marker.remove();
        }
    }

    private void onListener() {
        findViewById(R.id.btnAddRemoveMarker).setOnClickListener(this);
        findViewById(R.id.btnFitBounds).setOnClickListener(this);
        findViewById(R.id.btnUseInfoWindow).setOnClickListener(this);
    }

    @Override
    public void onMapReady(Map4D map4D) {
        this.map4D = map4D;
        map4D.moveCamera(MFCameraUpdateFactory.newCoordinateZoom(new MFLocationCoordinate(12.8006f, 106.784f), 6));
        addMarkersToMap();
        map4D.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        map4D.setOnMarkerClickListener(new Map4D.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(MFMarker mfMarker) {
                OnMarkerClick(mfMarker);
                return false;
            }
        });
        map4D.setOnUserPOIClickListener(new Map4D.OnUserPOIClickListener() {
            @Override
            public void onUserPOIClick(MFPOI mfpoi) {
                Toast.makeText(MarkerActivity.this, "User Poi Clicked: " + mfpoi.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        map4D.setOnPOIClickListener(new Map4D.OnPOIClickListener() {
            @Override
            public void onPOIClick(String placeId, String title, MFLocationCoordinate location) {

            }
        });
        map4D.enable3DMode(true);
        MFBuildingOptions extrudeBuildingOptions = new MFBuildingOptions().location(new MFLocationCoordinate(10.774544, 106.707764))
          .name("Extrude Building")
          .model(
            new ArrayList<MFLocationCoordinate>(Arrays.asList(new MFLocationCoordinate(10.774544, 106.707764), new MFLocationCoordinate(10.773766, 106.709001),
              new MFLocationCoordinate(10.772759, 106.708627), new MFLocationCoordinate( 10.774045, 106.707806))))
          .height(100.0);
        map4D.addBuilding(extrudeBuildingOptions);
        map4D.setFilterPlaces(new ArrayList());
        map4D.setOnMarkerDragListener(new Map4D.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(MFMarker mfMarker) {
                Log.e("Chung", "Dragging");
            }

            @Override
            public void onMarkerDragEnd(MFMarker mfMarker) {
                Log.e("Chung", "End");
            }

            @Override
            public void onMarkerDragStart(MFMarker mfMarker) {
                Log.e("Chung", "Start");
            }
        });
        map4D.setOnBuildingClickListener(new Map4D.OnBuildingClickListener() {
            @Override
            public void onBuildingClick(String buildingId, String name, MFLocationCoordinate location) {
                MarkerActivity.this.map4D.setSelectedBuildings(Arrays.asList(buildingId));
            }
        });
        map4D.enable3DMode(true);
        MFBuildingOptions buildingOptions = new MFBuildingOptions();
        buildingOptions.location(new MFLocationCoordinate(16.088987, 108.227940))
          .name("Test Building")
          .model("https://sw-hcm-1.vinadata.vn/v1/AUTH_d0ecabcbdcd74f6aa6ac9a5da528eb78/sdk/models/5b21d9a5cd18d02d045a5e99")
          .texture("https://sw-hcm-1.vinadata.vn/v1/AUTH_d0ecabcbdcd74f6aa6ac9a5da528eb78/sdk/textures/0cb35e1610c34e55946a7839356d8f66.jpg");
        map4D.addBuilding(buildingOptions);
        map4D.moveCamera(MFCameraUpdateFactory.newCoordinate(new MFLocationCoordinate(16.088987, 108.227940)));
        map4D.setOnUserBuildingClickListener(new Map4D.OnUserBuildingClickListener() {
            @Override
            public void onUserBuildingClick(MFBuilding mfBuilding) {
                mfBuilding.setSelected(true);
            }
        });
    }

    private void OnMarkerClick(MFMarker mfMarker) {
        lastSelectedMarker = mfMarker;
        Toast.makeText(getApplicationContext(), "Maker Id: " + mfMarker.getId(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddRemoveMarker: {
                clearMarkers = !clearMarkers;
                if (clearMarkers) {
                    removeMakersFromMap();
                    ((Button)findViewById(R.id.btnAddRemoveMarker)).setText(R.string.addMarker);
                }
                else {
                    addMarkersToMap();
                    ((Button)findViewById(R.id.btnAddRemoveMarker)).setText(R.string.removeMarker);
                }
                break;
            }
            case R.id.btnFitBounds: {
                MFCoordinateBounds.Builder builder = new MFCoordinateBounds.Builder();
                for (MFMarker marker : markersList) {
                    builder.include(marker.getPosition());
                }
                MFCameraPosition cameraPosition = map4D.getCameraPositionForBounds(builder.build(), 10);
                map4D.moveCamera(MFCameraUpdateFactory.newCameraPosition(cameraPosition));
                break;
            }
            case R.id.btnUseInfoWindow: {
                defaultInfoWindow = !defaultInfoWindow;
                if (defaultInfoWindow) {
                    ((Button)findViewById(R.id.btnUseInfoWindow)).setText(R.string.customInfoWindow);
                }
                else {
                    ((Button)findViewById(R.id.btnUseInfoWindow)).setText(R.string.defaultInfoWindow);
                }
                if (lastSelectedMarker != null && lastSelectedMarker.isInfoWindowShown()) {
                    lastSelectedMarker.hideInfoWindow();
                }
                break;
            }
        }
    }
}
