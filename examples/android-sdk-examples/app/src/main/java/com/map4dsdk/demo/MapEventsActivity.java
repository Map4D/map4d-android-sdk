package com.map4dsdk.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import vn.map4d.map.annotations.MFBuilding;
import vn.map4d.map.camera.MFCameraPosition;
import vn.map4d.map.camera.MFCameraUpdateFactory;
import vn.map4d.types.MFLocationCoordinate;
import vn.map4d.map.core.MFSupportMapFragment;
import vn.map4d.map.core.Map4D;
import vn.map4d.map.core.OnMapReadyCallback;

public class MapEventsActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private Map4D map4D;
    private TextView txtMapState;
    private TextView txtCameraInfo;
    private boolean mode3D = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_events_activity);
        txtMapState = findViewById(R.id.txtMapState);
        txtCameraInfo = findViewById(R.id.txtCameraInfo);
        MFSupportMapFragment mapFragment = (MFSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapEventsDemo);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setTitle(R.string.mapEvent);
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
        findViewById(R.id.btnSwitchMode).setOnClickListener(this);
    }

    private void updateCameraInfo() {
        if (map4D != null) {
            MFCameraPosition cameraPosition = map4D.getCameraPosition();
            txtCameraInfo.setText("camera zoom:  " + cameraPosition.getZoom() + "  bearing;   " + Math.round(cameraPosition.getBearing())
                    + "  tilt:  " + Math.round(cameraPosition.getTilt()));
        }
    }

    @Override
    public void onMapReady(final Map4D map4D) {
        this.map4D = map4D;
        map4D.moveCamera(MFCameraUpdateFactory.newCoordinateZoom(new MFLocationCoordinate(16.066517, 108.210354), 14));
        updateCameraInfo();
        map4D.setOnMapClickListener(new Map4D.OnMapClickListener() {
            @Override
            public void onMapClick(MFLocationCoordinate latLng) {
                txtMapState.setText("map click: " + latLng.getLatitude() + ", " + latLng.getLongitude());
            }
        });

        map4D.setOnCameraIdleListener(new Map4D.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                txtMapState.setText(R.string.idleState);
                updateCameraInfo();
            }
        });

        map4D.setOnCameraMoveListener(new Map4D.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                txtMapState.setText(R.string.movingState);
            }
        });

        map4D.setOnCameraMoveStartedListener(new Map4D.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int reason) {
                txtMapState.setText(R.string.startedState);
            }
        });

        map4D.setOnPOIClickListener(new Map4D.OnPOIClickListener() {
            @Override
            public void onPOIClick(String placeId, String name, MFLocationCoordinate location) {
                txtMapState.setText("Click POI: " + name);
            }
        });

        map4D.setOnBuildingClickListener(new Map4D.OnBuildingClickListener() {
            @Override
            public void onBuildingClick(String s, String s1, MFLocationCoordinate mfLocationCoordinate) {
                map4D.setSelectedBuildings(Arrays.asList(s));
            }
        });

        map4D.setOnPlaceClickListener(new Map4D.OnPlaceClickListener() {
            @Override
            public void onPlaceClick(@NonNull String name, @NonNull MFLocationCoordinate location) {
                txtMapState.setText("Click place: " + name);
            }
        });

        map4D.setOnMapModeChange(new Map4D.OnMapModeChangeListener() {
            @Override
            public void onMapModeChange(boolean is3DMode) {
                Toast.makeText(getApplicationContext(), is3DMode ? "2D->3D" : "3D->2D", Toast.LENGTH_SHORT).show();
                if (is3DMode) {
                    ((Button) findViewById(R.id.btnSwitchMode)).setText(R.string.mode2d);
                }
                else {
                    ((Button) findViewById(R.id.btnSwitchMode)).setText(R.string.mode3d);
                }
                mode3D = is3DMode;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSwitchMode: {
                if (map4D != null) {
                    map4D.enable3DMode(!mode3D);
                }
                break;
            }
        }
    }
}
