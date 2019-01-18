package com.map4dsdk.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import vn.map4d.map4dsdk.camera.MFCameraUpdateFactory;
import vn.map4d.map4dsdk.maps.LatLng;
import vn.map4d.map4dsdk.maps.LatLngBounds;
import vn.map4d.map4dsdk.maps.MFSupportMapFragment;
import vn.map4d.map4dsdk.maps.MFTileArea;
import vn.map4d.map4dsdk.maps.MFTileAreaOptions;
import vn.map4d.map4dsdk.maps.Map4D;
import vn.map4d.map4dsdk.maps.OnMapReadyCallback;

public class TileAreaActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private Map4D map4D;
    private MFTileArea tileArea;
    private LatLngBounds latLngBounds = null;
    private boolean tileAreaAdded = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tile_area_activity);
        MFSupportMapFragment mapFragment = (MFSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.tileAreaDemo);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setTitle(R.string.tileArea);
        setOnListener();
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
        findViewById(R.id.btnAddRemoveTileArea).setOnClickListener(this);
        findViewById(R.id.btnUpdateTileArea).setOnClickListener(this);
    }

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

    private void updateTileArea() {
        latLngBounds = latLngBounds.include(new LatLng(14.903115, 108.192587));
        if (tileArea != null) {
            tileArea.setBounds(latLngBounds);
        }
    }

    private void removeTileAreaFromMap() {
        if (tileArea != null) {
            tileArea.remove();
            tileArea = null;
        }
    }

    @Override
    public void onMapReady(Map4D map4D) {
        this.map4D = map4D;
        map4D.moveCamera(MFCameraUpdateFactory.newLatLngZoom(new LatLng(16.066517, 108.210354), 12));
        addTileAreaToMap();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddRemoveTileArea: {
                tileAreaAdded = !tileAreaAdded;
                if (tileAreaAdded) {
                    addTileAreaToMap();
                    ((Button) findViewById(R.id.btnAddRemoveTileArea)).setText(R.string.removeTileArea);
                }
                else {
                    removeTileAreaFromMap();
                    ((Button) findViewById(R.id.btnAddRemoveTileArea)).setText(R.string.addTileArea);
                }
                break;
            }
            case R.id.btnUpdateTileArea: {
                updateTileArea();
                break;
            }
        }
    }
}
