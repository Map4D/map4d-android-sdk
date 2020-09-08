package com.map4dsdk.demo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.map4d.map.annotations.MFMarker;
import vn.map4d.map.annotations.MFMarkerOptions;
import vn.map4d.map.annotations.MFPolyline;
import vn.map4d.map.annotations.MFPolylineOptions;
import vn.map4d.map.camera.MFCameraUpdateFactory;
import vn.map4d.map.core.MFSupportMapFragment;
import vn.map4d.map.core.Map4D;
import vn.map4d.map.core.OnMapReadyCallback;
import vn.map4d.types.MFLocationCoordinate;

public class PolylineActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private Map4D map4D;
    private boolean polylineAdded = true;
    private final List<MFLocationCoordinate> latLngList = new ArrayList<>();
    private final List<MFMarker> markersList = new ArrayList<>();
    private boolean pathUpdated = false;
    private MFPolyline polyline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polyline_activity);
        MFSupportMapFragment mapFragment = (MFSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.polylineDemo);
        mapFragment.getMapAsync(this);
        setOnListener();
        getSupportActionBar().setTitle(R.string.polyline);
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

    private void setOnListener() {
        findViewById(R.id.btnAddRemovePolyline).setOnClickListener(this);
        findViewById(R.id.btnShowHidePolyline).setOnClickListener(this);
        findViewById(R.id.btnUpdatePath).setOnClickListener(this);
    }

    private void createPath() {
        latLngList.add(new MFLocationCoordinate(16.067218, 108.213916));
        latLngList.add(new MFLocationCoordinate(16.066496, 108.210311));
        latLngList.add(new MFLocationCoordinate(16.064877, 108.210397));
        latLngList.add(new MFLocationCoordinate(16.059980, 108.211137));
        latLngList.add(new MFLocationCoordinate(16.059516, 108.208358));

    }

    private void addMarkersToMap() {
        int i = 1;
        for (MFLocationCoordinate latLng : latLngList) {
            MFMarker marker = map4D.addMarker(new MFMarkerOptions()
                    .position(latLng)
                    .title("Marker " + i++)
                    .snippet(latLng.getLatitude() + ", " + latLng.getLongitude()));
            markersList.add(marker);
        }
    }

    private void removeMarkersFromMap() {
        for (MFMarker marker : markersList) {
            marker.remove();
        }
        markersList.clear();
    }

    private void addPolylineToMap() {
        polyline = map4D.addPolyline(new MFPolylineOptions().add(latLngList.toArray(new MFLocationCoordinate[latLngList.size()]))
                .color("#0000ff")
                .width(8)
                .alpha(0.3f));
        addMarkersToMap();
    }

    private void removePolyline() {
        polyline.remove();
        polyline = null;
        removeMarkersFromMap();
    }

    /**
     * update Path of Polyline
     */
    private void addLatLngToPath () {
        latLngList.add(new MFLocationCoordinate(16.058691, 108.206046));
        latLngList.add(new MFLocationCoordinate(16.057866, 108.203605));
        polyline.setPath(latLngList);
        int size = latLngList.size();
        MFLocationCoordinate latLng = latLngList.get(size - 2);
        MFLocationCoordinate latLng1 = latLngList.get(size - 1);
        markersList.add(map4D.addMarker(new MFMarkerOptions()
                .position(latLng)
                .title("Marker " + size ++)
                .snippet(latLng.getLatitude() + ", " + latLng.getLongitude())));
        markersList.add(map4D.addMarker(new MFMarkerOptions()
                .position(latLng1)
                .title("Marker " + size ++)
                .snippet(latLng1.getLatitude() + ", " + latLng1.getLongitude())));
    }

    private void removeLatLngFromPath() {
        int size = latLngList.size();
        latLngList.remove(size - 1);
        latLngList.remove(size - 2);
        polyline.setPath(latLngList);
        markersList.get(size - 1).remove();
        markersList.get(size - 2).remove();
        markersList.remove(size - 1);
        markersList.remove(size - 2);
    }

    @Override
    public void onMapReady(Map4D map4D) {
        this.map4D = map4D;
        createPath();
        map4D.moveCamera(MFCameraUpdateFactory.newCoordinateZoom(new MFLocationCoordinate(16.064877, 108.210397), 16));
        addPolylineToMap();
        map4D.setOnPolylineClickListener(new Map4D.OnPolylineClickListener() {
            @Override
            public void onPolylineClick(MFPolyline polyline) {
                Toast.makeText(getApplicationContext(), "clicked Polyline: id " + polyline.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddRemovePolyline: {
                polylineAdded = !polylineAdded;
                if (polylineAdded) {
                    addPolylineToMap();
                    ((Button)findViewById(R.id.btnAddRemovePolyline)).setText(R.string.removePolyline);
                }
                else {
                    removePolyline();
                    ((Button)findViewById(R.id.btnAddRemovePolyline)).setText(R.string.addPolyline);
                }
                break;
            }
            case R.id.btnShowHidePolyline: {
                if (polyline != null  && polyline.isVisible()) {
                    polyline.setVisible(false);
                    ((Button) findViewById(R.id.btnShowHidePolyline)).setText(R.string.hidePolyline);
                }
                else if (polyline != null && !polyline.isVisible()) {
                    polyline.setVisible(true);
                    ((Button) findViewById(R.id.btnShowHidePolyline)).setText(R.string.showPolyline);
                }
                break;
            }
            case R.id.btnUpdatePath: {
                pathUpdated = !pathUpdated;
                if (pathUpdated) {
                    addLatLngToPath();
                }
                else {
                    removeLatLngFromPath();
                }
                break;
            }
        }
    }
}
