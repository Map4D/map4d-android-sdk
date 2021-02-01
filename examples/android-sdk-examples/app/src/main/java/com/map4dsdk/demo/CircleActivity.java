package com.map4dsdk.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import vn.map4d.map.annotations.MFCircle;
import vn.map4d.map.annotations.MFCircleOptions;
import vn.map4d.map.camera.MFCameraUpdateFactory;
import vn.map4d.types.MFLocationCoordinate;
import vn.map4d.map.core.MFSupportMapFragment;
import vn.map4d.map.core.Map4D;
import vn.map4d.map.core.OnMapReadyCallback;

public class CircleActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private Map4D map4D;
    private MFCircle circle;
    private boolean circleAdded = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_activity);
        MFSupportMapFragment mapFragment = (MFSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.circleDemo);
        mapFragment.getMapAsync(this);
        setOnListener();
        getSupportActionBar().setTitle(R.string.circle);
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
        findViewById(R.id.btnAddRemoveCircle).setOnClickListener(this);
        findViewById(R.id.btnShowHideCircle).setOnClickListener(this);
        findViewById(R.id.btnUpdateCircle).setOnClickListener(this);
    }

    private void addCircleToMap() {
        if (map4D != null) {
            circle = map4D.addCircle(new MFCircleOptions()
                        .center(new MFLocationCoordinate(16.066517, 108.210354))
                        .radius(500)
                        .fillColor(ContextCompat.getColor(this, R.color.greenWithAlpha)));
        }
    }

    private void removeCircleFromMap() {
        if (circle != null) {
            circle.remove();
            circle = null;
        }
    }
    private void updateCircle() {
        if (circle != null) {
            circle.setRadius(700);
            circle.setFillColor(ContextCompat.getColor(this, R.color.circleUpdateColor));
            circle.setStrokeColor(ContextCompat.getColor(this, R.color.circleStrokeColor));
            circle.setStrokeWidth(5);
        }
    }

    @Override
    public void onMapReady(Map4D map4D) {
        this.map4D = map4D;
        map4D.moveCamera(MFCameraUpdateFactory.newCoordinateZoom(new MFLocationCoordinate(16.066517, 108.210354), 14));
        addCircleToMap();
        map4D.setOnCircleClickListener(new Map4D.OnCircleClickListener() {
            @Override
            public void onCircleClick(MFCircle mfCircle) {
                Toast.makeText(getApplicationContext(), "Circle clicked:  " + mfCircle.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddRemoveCircle: {
                circleAdded = !circleAdded;
                if (circleAdded) {
                    addCircleToMap();
                    ((Button) findViewById(R.id.btnAddRemoveCircle)).setText(R.string.removeCircle);
                }
                else {
                    removeCircleFromMap();
                    ((Button) findViewById(R.id.btnAddRemoveCircle)).setText(R.string.addCircle);
                }
                break;
            }
            case R.id.btnShowHideCircle: {
                if (circle != null && circle.isVisible()) {
                    circle.setVisible(false);
                    ((Button) findViewById(R.id.btnShowHideCircle)).setText(R.string.showCircle);
                }
                else if (circle != null && !circle.isVisible()){
                    circle.setVisible(true);
                    ((Button) findViewById(R.id.btnShowHideCircle)).setText(R.string.hideCircle);
                }
                break;
            }
            case R.id.btnUpdateCircle: {
                updateCircle();
                break;
            }
        }
    }
}
