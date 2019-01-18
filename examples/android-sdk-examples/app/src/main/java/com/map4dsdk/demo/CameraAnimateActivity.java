package com.map4dsdk.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import vn.map4d.map4dsdk.camera.MFCameraUpdateFactory;
import vn.map4d.map4dsdk.maps.LatLng;
import vn.map4d.map4dsdk.maps.MFSupportMapFragment;
import vn.map4d.map4dsdk.maps.Map4D;
import vn.map4d.map4dsdk.maps.OnMapReadyCallback;

public class CameraAnimateActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private Map4D map4D;
    private final LatLng saj = new LatLng(10.770680, 106.703446);
    private final LatLng dad = new LatLng(16.066517, 108.210354);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_animate_activity);
        MFSupportMapFragment mapFragment = (MFSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.cameraAnimateDemo);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setTitle(R.string.cameraAnimate);
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
        findViewById(R.id.btnSaiGon).setOnClickListener(this);
        findViewById(R.id.btnMoveToSaiGon).setOnClickListener(this);
        findViewById(R.id.btnDaNang).setOnClickListener(this);
        findViewById(R.id.btnMoveToDaNang).setOnClickListener(this);
    }

    private void animateToSaiGon() {
        if(map4D != null) {
            map4D.animateCamera(MFCameraUpdateFactory.newLatLngZoom(saj, 14));
        }
    }

    private void animateToDaNang() {
        if(map4D != null) {
            map4D.animateCamera(MFCameraUpdateFactory.newLatLngZoom(dad, 14));
        }
    }

    private void moveToSaiGon() {
        if(map4D != null) {
            map4D.moveCamera(MFCameraUpdateFactory.newLatLngZoom(saj, 14));
        }
    }

    private void moveToDaNang() {
        if(map4D != null) {
            map4D.moveCamera(MFCameraUpdateFactory.newLatLngZoom(dad, 14));
        }
    }

    @Override
    public void onMapReady(Map4D map4D) {
        this.map4D = map4D;
        map4D.moveCamera(MFCameraUpdateFactory.newLatLngZoom(new LatLng(16.066517, 108.210354), 14));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSaiGon: {
                animateToSaiGon();
                break;
            }
            case R.id.btnMoveToSaiGon: {
                moveToSaiGon();
                break;
            }
            case R.id.btnDaNang: {
                animateToDaNang();
                break;
            }
            case R.id.btnMoveToDaNang: {
                moveToDaNang();
                break;
            }
        }
    }
}
