package com.map4dsdk.demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import vn.map4d.map.core.MFSupportMapFragment;
import vn.map4d.map.core.Map4D;
import vn.map4d.map.core.OnMapReadyCallback;

public class MyLocationActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private static final int REQUEST_LOCATION_CODE = 69;
    private Map4D map4D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_location_activity);
        MFSupportMapFragment mapFragment = (MFSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.myLocationDemo);
        mapFragment.getMapAsync(this);
        setOnListener();
        getSupportActionBar().setTitle(R.string.myLocation);
        if (!isLocationPermissionEnable()) {
            requestLocationPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});
        }
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
        findViewById(R.id.btnEnableLocation).setOnClickListener(this);
        findViewById(R.id.btnDisableLocation).setOnClickListener(this);
    }

    private void requestLocationPermission(String[] permission) {
        ActivityCompat.requestPermissions(this, permission, REQUEST_LOCATION_CODE);
    }

    boolean isLocationPermissionEnable() {
        boolean isLocationPermissionenabed = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isLocationPermissionenabed = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        }
        return isLocationPermissionenabed;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION_CODE: {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    map4D.setMyLocationEnabled(true);
                    map4D.setOnMyLocationButtonClickListener(new Map4D.OnMyLocationButtonClickListener() {
                        @Override
                        public boolean onMyLocationButtonClick() {
                            Toast.makeText(getApplicationContext(), "My Location button clicked", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    });
                } else {
                    Toast.makeText(this, "Need Allow Location Permission to use Location feature", Toast.LENGTH_LONG).show();
                }
            }
            break;
        }
    }

    @Override
    public void onMapReady(Map4D map4D) {
        this.map4D = map4D;
        map4D.setOnMyLocationButtonClickListener(new Map4D.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Toast.makeText(getApplicationContext(), "My Location Button clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        map4D.setOnMyLocationClickListener(new Map4D.OnMyLocationClickListener() {
            @Override
            public void onMyLocationClick(Location location) {
                Toast.makeText(getApplicationContext(), "My Location Icon clicked", Toast.LENGTH_SHORT).show();
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map4D.setMyLocationEnabled(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEnableLocation: {
                if (map4D != null && !map4D.isMyLocationEnabled()) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        requestLocationPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});
                        return;
                    }
                    map4D.setMyLocationEnabled(true);
                }
                break;
            }
            case R.id.btnDisableLocation: {
                if (map4D != null && map4D.isMyLocationEnabled()) {
                    map4D.setMyLocationEnabled(false);
                }
            }
        }
    }
}
