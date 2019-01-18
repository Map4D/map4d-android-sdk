package com.map4dsdk.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.map4d.map4dsdk.annotations.MFPolygon;
import vn.map4d.map4dsdk.annotations.MFPolygonOptions;
import vn.map4d.map4dsdk.camera.MFCameraUpdateFactory;
import vn.map4d.map4dsdk.maps.LatLng;
import vn.map4d.map4dsdk.maps.MFSupportMapFragment;
import vn.map4d.map4dsdk.maps.Map4D;
import vn.map4d.map4dsdk.maps.OnMapReadyCallback;

public class PolygonActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private Map4D map4D;
    private boolean polygonAdded = true;
    private final List<LatLng> pointsList = new ArrayList<>();
    private final List<LatLng> holePath = new ArrayList<>();
    private MFPolygon polygon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polygon_activity);
        MFSupportMapFragment mapFragment = (MFSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.polygonDemo);
        mapFragment.getMapAsync(this);
        setOnListener();
        getSupportActionBar().setTitle(R.string.polygon);
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
        findViewById(R.id.btnAddRemovePolygon).setOnClickListener(this);
        findViewById(R.id.btnShowHidePolygon).setOnClickListener(this);
        findViewById(R.id.btnUpdatePolygonPath).setOnClickListener(this);
    }

    private void createPointsList() {
        pointsList.add(new LatLng(16.066517, 108.210354));
        pointsList.add(new LatLng(16.067243, 108.214077));
        pointsList.add(new LatLng(16.065419, 108.214576));
        pointsList.add(new LatLng(16.062815, 108.214034));
        pointsList.add(new LatLng(16.062434, 108.210772));
        pointsList.add(new LatLng(16.066517, 108.210354));
    }

    private void createHole() {
        holePath.add(new LatLng(16.065681, 108.211716));
        holePath.add(new LatLng(16.065898, 108.213009));
        holePath.add(new LatLng(16.065336, 108.213202));
        holePath.add(new LatLng(16.064965, 108.212183));
        holePath.add(new LatLng(16.065681, 108.211716));
    }

    private void addPolygonToMap() {
        createHole();
        createPointsList();
        polygon = map4D.addPolygon(new MFPolygonOptions()
                .add(pointsList.toArray(new LatLng[pointsList.size()]))
                .addHole(holePath.toArray(new LatLng[holePath.size()]))
                .fillColor("#0000ff")
                .alpha(0.5f));
    }

    private void updatePolygon() {
        updatePolygonPoints();
        if (polygon != null) {
            polygon.setFillColor("#00ff00");
            polygon.setFillAlpha(1.f);
        }
    }

    private void updatePolygonPoints() {
        int size = pointsList.size();
        pointsList.remove(size - 1);
        pointsList.remove(size - 2);
        pointsList.remove(size - 3);
        pointsList.add(new LatLng(16.060743, 108.216791));
        pointsList.add(new LatLng(16.059918, 108.211105));
        pointsList.add(new LatLng(16.066517, 108.210354));
        if (polygon != null) {
            polygon.setPoints(pointsList);
        }
    }

    private void removePolygonToMap() {
        if (polygon != null) {
            polygon.remove();
            polygon = null;
            holePath.clear();
            pointsList.clear();
        }
    }

    @Override
    public void onMapReady(Map4D map4D) {
        this.map4D = map4D;
        map4D.moveCamera(MFCameraUpdateFactory.newLatLngZoom(new LatLng(16.065336, 108.213202), 15));
        addPolygonToMap();
        map4D.setOnPolygonClickListener(new Map4D.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(MFPolygon polygon) {
                Toast.makeText(getApplicationContext(), "Clicked Polygon: ID " + polygon.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddRemovePolygon: {
                polygonAdded = !polygonAdded;
                if (polygonAdded) {
                    addPolygonToMap();
                    ((Button) findViewById(R.id.btnAddRemovePolygon)).setText(R.string.removePolygon);
                }
                else {
                    removePolygonToMap();
                    ((Button) findViewById(R.id.btnAddRemovePolygon)).setText(R.string.addPolygon);
                }
                break;
            }
            case R.id.btnShowHidePolygon: {
                if (polygon != null && polygon.isVisible()) {
                    polygon.setVisible(false);
                    ((Button) findViewById(R.id.btnShowHidePolygon)).setText(R.string.showPolygon);
                }
                else if (polygon != null && !polygon.isVisible()){
                    polygon.setVisible(true);
                    ((Button) findViewById(R.id.btnShowHidePolygon)).setText(R.string.hidePolygon);
                }
                break;
            }
            case R.id.btnUpdatePolygonPath: {
                if (polygon != null) {
                    updatePolygon();
                }
                break;
            }
        }
    }
}
