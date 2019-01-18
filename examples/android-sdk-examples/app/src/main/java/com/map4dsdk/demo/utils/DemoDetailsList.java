package com.map4dsdk.demo.utils;

import com.map4dsdk.demo.CameraAnimateActivity;
import com.map4dsdk.demo.CircleActivity;
import com.map4dsdk.demo.MapEventsActivity;
import com.map4dsdk.demo.MarkerActivity;
import com.map4dsdk.demo.MyLocationActivity;
import com.map4dsdk.demo.PolygonActivity;
import com.map4dsdk.demo.PolylineActivity;
import com.map4dsdk.demo.R;
import com.map4dsdk.demo.SettingsActivity;
import com.map4dsdk.demo.TileAreaActivity;

public class DemoDetailsList {
    public static final DemoDetails[] demos = {
            new DemoDetails(R.string.settings, R.drawable.ic_settings, SettingsActivity.class),
            new DemoDetails(R.string.marker, R.drawable.ic_marker, MarkerActivity.class),
            new DemoDetails(R.string.polyline, R.drawable.ic_polyline, PolylineActivity.class),
            new DemoDetails(R.string.polygon, R.drawable.ic_polygon, PolygonActivity.class),
            new DemoDetails(R.string.circle, R.drawable.ic_circle, CircleActivity.class),
            new DemoDetails(R.string.tileArea, R.drawable.ic_tile_area, TileAreaActivity.class),
            new DemoDetails(R.string.myLocation, R.drawable.ic_location, MyLocationActivity.class),
            new DemoDetails(R.string.mapEvent, R.drawable.ic_map_events, MapEventsActivity.class),
            new DemoDetails(R.string.cameraAnimate, R.drawable.ic_camera_animate, CameraAnimateActivity.class)
    };
}
