package com.map4dsdk.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_tile_overlay.*
import vn.map4d.map.core.Map4D
import vn.map4d.map.core.OnMapReadyCallback
import vn.map4d.map.overlays.MFTileOverlayOptions
import vn.map4d.map.overlays.MFTileProvider

class TileOverlayActivity : AppCompatActivity(), OnMapReadyCallback {
  private lateinit var map4D: Map4D

  private val tileProvider: MFTileProvider =  MFTileProvider() { x: Int, y: Int, zoom: Int, _3dMode: Boolean ->
    return@MFTileProvider if (_3dMode) {
      null
    } else "https://tile.openstreetmap.de/$zoom/$x/$y.png"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_tile_overlay)
    tileOverlayMap?.getMapAsync(this)
    supportActionBar?.title = getString(R.string.tileOverlay)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id: Int = item.itemId
    if (id == android.R.id.home) {
      onBackPressed()
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onMapReady(map4D: Map4D) {
    this.map4D = map4D
    val tileOverlay = this.map4D.addTileOverlay(MFTileOverlayOptions().tileProvider(tileProvider))
  }

  override fun onDestroy() {
    tileOverlayMap?.onDestroy()
    super.onDestroy()
  }
}