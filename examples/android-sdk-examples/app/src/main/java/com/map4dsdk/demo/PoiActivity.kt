package com.map4dsdk.demo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_poi.*
import kotlinx.android.synthetic.main.activity_tile_overlay.*
import vn.map4d.map.annotations.MFPOI
import vn.map4d.map.annotations.MFPOIOptions
import vn.map4d.map.core.Map4D
import vn.map4d.map.core.OnMapReadyCallback
import vn.map4d.types.MFLocationCoordinate
import kotlin.random.Random

class PoiActivity : AppCompatActivity(), OnMapReadyCallback {
  private lateinit var map4D: Map4D

  private val poiList = mutableListOf<MFPOI>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_poi)
    poiMap?.getMapAsync(this)
    supportActionBar?.title = getString(R.string.poi)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    setOnClickListener()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id: Int = item.itemId
    if (id == android.R.id.home) {
      onBackPressed()
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  private fun setOnClickListener() {
    addPoi?.setOnClickListener {
      addPoi()
    }
    removePoi?.setOnClickListener {
      val poi = poiList.removeLastOrNull()
      poi?.remove()
    }
  }

  private fun addPoi() {
    if (this::map4D.isInitialized) {
      val location = map4D.cameraPosition.target
      val random = Random.nextDouble(-0.001, 0.001)
      val userPOIOptions = MFPOIOptions()
      userPOIOptions.position(MFLocationCoordinate(location.getLatitude() - random, location.getLongitude() - random))
        .title("Test User POI ${poiList.size + 1}")
        .titleColor(Color.GREEN)
        .subtitle("Da Nang")
      val poi = map4D.addPOI(userPOIOptions)
      poiList.add(poi)
    }
  }

  override fun onMapReady(map4D: Map4D) {
    this.map4D = map4D
    map4D.isPOIsEnabled = false
    map4D.setOnUserPOIClickListener {
       Toast.makeText(this, "Poi: ${it.title} is clicked", Toast.LENGTH_SHORT).show()
    }
  }
  override fun onDestroy() {
    poiMap?.onDestroy()
    super.onDestroy()
  }
}