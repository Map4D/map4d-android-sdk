package com.map4dsdk.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.map4d.map4dsdk.camera.MFCameraUpdateFactory;
import vn.map4d.map4dsdk.maps.LatLng;
import vn.map4d.map4dsdk.maps.MFSupportMapFragment;
import vn.map4d.map4dsdk.maps.MFSwitchMode;
import vn.map4d.map4dsdk.maps.Map4D;
import vn.map4d.map4dsdk.maps.OnMapReadyCallback;

public class Simple2DMapActivity extends AppCompatActivity implements OnMapReadyCallback{

    private MFSupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_2dmap_activity);
        mapFragment = (MFSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2D);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setTitle(R.string.map2dDemo);
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

    @Override
    public void onMapReady(Map4D map4D) {
        map4D.setSwitchMode(MFSwitchMode.Manual);
        map4D.moveCamera(MFCameraUpdateFactory.newLatLngZoom(new LatLng(10.771666, 106.704405), 16));
        //map4D.addMarker(new MFMarkerOptions().title("Quan 1").snippet("Trung Tam Hanh Chinh").position(new LatLng(10.771666, 106.704405)));
    }
}
