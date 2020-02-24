package vn.map4d.simplemap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import vn.map4d.map.core.Map4D
import vn.map4d.map.core.OnMapReadyCallback

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onMapReady(map4D: Map4D?) {
        map4D?.enable3DMode(true)
    }

    override fun onDestroy() {
        mapView?.onDestroy()
        super.onDestroy()
    }
}
