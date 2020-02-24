package com.map4dsdk.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vn.map4d.map.core.MFSupportMapFragment;
import vn.map4d.map.core.MFSwitchMode;
import vn.map4d.map.core.Map4D;
import vn.map4d.map.core.OnMapReadyCallback;
import vn.map4d.types.MFLocationCoordinate;

public class SettingsActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private Map4D map4D;
    private boolean mode3D = true;
    private int currentMode = 2;
    private TextView txtMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        MFSupportMapFragment mapFragment = (MFSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.settingsDemo);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setTitle(R.string.settings);
        txtMode = findViewById(R.id.modeSwitch);
        onListener();
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

    public void onListener() {
        findViewById(R.id.btnSetTime).setOnClickListener(this);
        findViewById(R.id.btnEnable3D).setOnClickListener(this);
        findViewById(R.id.btnSwitchMode3D).setOnClickListener(this);
    }

    @Override
    public void onMapReady(Map4D map4D) {
        this.map4D = map4D;
        map4D.enable3DMode(true);
        map4D.setMinZoomPreference(5.f);
        map4D.setMaxZoomPreference(19.f);
        map4D.setMaxNativeZoom(17.f);
        map4D.setOnMapClickListener(new Map4D.OnMapClickListener() {
            @Override
            public void onMapClick(MFLocationCoordinate latLng) {
                ((TextView)findViewById(R.id.lat)).setText("Lat:    " + latLng.getLatitude());
                ((TextView)findViewById(R.id.lng)).setText("Lng:   " + latLng.getLongitude());
            }
        });

        map4D.setOnMapModeChange(new Map4D.OnMapModeChangeListener() {
            @Override
            public void onMapModeChange(boolean is3DMode) {
                Toast.makeText(getApplicationContext(), is3DMode ? "2D->3D" : "3D->2D", Toast.LENGTH_SHORT).show();
                if (is3DMode) {
                    ((Button) findViewById(R.id.btnEnable3D)).setText(R.string.mode2d);
                }
                else {
                    ((Button) findViewById(R.id.btnEnable3D)).setText(R.string.mode3d);
                }
                mode3D = is3DMode;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSetTime: {
                String givenDateString = "2000-01-01";
                ((TextView) findViewById(R.id.time)).setText(givenDateString);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date mDate = sdf.parse(givenDateString);
                    map4D.setTime(mDate);
                } catch (ParseException e) {
                }
                break;
            }
            case R.id.btnEnable3D: {
                if (map4D != null) {
                    map4D.enable3DMode(!mode3D);
                }
                break;
            }
            case R.id.btnSwitchMode3D: {
                if (map4D != null) {
                    MFSwitchMode switchType;
                    ++currentMode;
                    switch (currentMode) {
                        case 1: {
                            switchType = MFSwitchMode.Auto2DTo3D;
                            Toast.makeText(getApplicationContext(), "Switch From Manual To 2D->3D", Toast.LENGTH_SHORT).show();
                            ((Button) findViewById(R.id.btnSwitchMode3D)).setText(R.string.mode3dTo2d);
                            txtMode.setText(R.string.mode2dTo3d);
                            break;
                        }
                        case 2: {
                            switchType = MFSwitchMode.Auto3DTo2D;
                            Toast.makeText(getApplicationContext(), "Switch From 2D->3D To 3D->2D", Toast.LENGTH_SHORT).show();
                            ((Button) findViewById(R.id.btnSwitchMode3D)).setText(R.string.auto);
                            txtMode.setText(R.string.mode3dTo2d);
                            break;
                        }
                        case 3: {
                            switchType = MFSwitchMode.Auto;
                            Toast.makeText(getApplicationContext(), "Switch From 3D->2D To Auto", Toast.LENGTH_SHORT).show();
                            ((Button) findViewById(R.id.btnSwitchMode3D)).setText(R.string.manual);
                            txtMode.setText(R.string.auto);
                            break;
                        }
                        case 4: {
                            switchType = MFSwitchMode.Manual;
                            Toast.makeText(getApplicationContext(), "Switch From Auto To Manual", Toast.LENGTH_SHORT).show();
                            ((Button) findViewById(R.id.btnSwitchMode3D)).setText(R.string.mode2dTo3d);
                            txtMode.setText(R.string.manual);
                            currentMode = 0;
                            break;
                        }
                        default: {
                            switchType = MFSwitchMode.Default;
                            Toast.makeText(getApplicationContext(), "Current", Toast.LENGTH_SHORT).show();
                            ((Button) findViewById(R.id.btnSwitchMode3D)).setText(R.string.mode2dTo3d);
                            txtMode.setText("Default");
                            currentMode = 0;
                            break;
                        }
                    }
                    map4D.setSwitchMode(switchType);
                }
                break;
            }
        }
    }
}
