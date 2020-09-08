package com.map4dsdk.demo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import vn.map4d.map.camera.MFCameraUpdateFactory;
import vn.map4d.map.core.MFCoordinateBounds;
import vn.map4d.map.core.MFSupportMapFragment;
import vn.map4d.map.core.MFTileArea;
import vn.map4d.map.core.MFTileAreaOptions;
import vn.map4d.map.core.Map4D;
import vn.map4d.map.core.OnMapReadyCallback;
import vn.map4d.types.MFLocationCoordinate;

public class TileAreaActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private Map4D map4D;
    private MFTileArea tileArea;
    private MFCoordinateBounds bounds = null;
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
        List<MFLocationCoordinate> pointsList = new ArrayList<>();
        pointsList.add(new MFLocationCoordinate(16.058227, 108.200483));
        pointsList.add(new MFLocationCoordinate(16.074311, 108.212628));
        pointsList.add(new MFLocationCoordinate(16.073115, 108.192587));
        bounds = new MFCoordinateBounds.Builder().includes(pointsList).build();
    }

    private void addTileAreaToMap() {
        createLatLngBounds();
        if (map4D != null && bounds != null && tileArea == null) {
            tileArea = map4D.addTileArea(new MFTileAreaOptions()
                    .bounds(bounds)
                    .mapUrl("https://a.tile.openstreetmap.org/{z}/{x}/{y}.png")
                    .minZoom(5.)
                    .maxZoom(20.));
        }
    }

    private void updateTileArea() {
        bounds = bounds.include(new MFLocationCoordinate(14.903115, 108.192587));
        if (tileArea != null) {
            tileArea.setBounds(bounds);
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
        map4D.moveCamera(MFCameraUpdateFactory.newCoordinateZoom(new MFLocationCoordinate(16.066517, 108.210354), 12));
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
