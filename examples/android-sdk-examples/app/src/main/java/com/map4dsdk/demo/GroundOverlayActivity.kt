package com.map4dsdk.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_ground_overlay.*
import kotlinx.android.synthetic.main.activity_tile_overlay.*
import vn.map4d.map.camera.MFCameraUpdateFactory
import vn.map4d.map.core.MFCoordinateBounds
import vn.map4d.map.core.Map4D
import vn.map4d.map.core.OnMapReadyCallback
import vn.map4d.map.overlays.MFGroundOverlayOptions
import vn.map4d.map.overlays.MFGroundProvider
import vn.map4d.map.overlays.MFUrlGroundProvider
import vn.map4d.types.MFLocationCoordinate

class GroundOverlayActivity : AppCompatActivity(), OnMapReadyCallback {
  private lateinit var map4D: Map4D

  private val groundProvider: MFGroundProvider = object : MFUrlGroundProvider() {
    override fun getGroundUrl(x: Int, y: Int, zoom: Int, _3dMode: Boolean): String? {
      return if (_3dMode) {
        null
      } else "https://tile.openstreetmap.de/$zoom/$x/$y.png"
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id: Int = item.itemId
    if (id == android.R.id.home) {
      onBackPressed()
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  private val bounds = MFCoordinateBounds(
    MFLocationCoordinate(16.057814922971613, 108.22065353393553),
    MFLocationCoordinate(16.064289641988594, 108.2324981689453))

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_ground_overlay)
    groundOverlayMap?.getMapAsync(this)
    supportActionBar?.title  = getString(R.string.groundOverlay)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun onMapReady(map4D: Map4D) {
    this.map4D = map4D
    map4D.addGroundOverlay(MFGroundOverlayOptions().groundProvider(groundProvider)
      .bounds(bounds))
    val cameraPosition = map4D.getCameraPositionForBounds(bounds, 10)
    map4D.moveCamera(MFCameraUpdateFactory.newCoordinate(cameraPosition.target))
  }
  override fun onDestroy() {
    groundOverlayMap?.onDestroy()
    super.onDestroy()
  }
}