package com.map4dsdk.demo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vn.map4d.map.core.MFSupportMapFragment;
import vn.map4d.map.core.Map4D;
import vn.map4d.map.core.OnMapReadyCallback;
import vn.map4d.types.MFLocationCoordinate;

public class SettingsActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private Map4D map4D;
    private boolean mode3D = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        MFSupportMapFragment mapFragment = (MFSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.settingsDemo);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setTitle(R.string.settings);;
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
        }
    }
}
