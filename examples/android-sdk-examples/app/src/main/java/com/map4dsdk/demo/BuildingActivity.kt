package com.map4dsdk.demo

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_building.*
import vn.map4d.map.annotations.MFBuilding
import vn.map4d.map.annotations.MFBuildingOptions
import vn.map4d.map.core.Map4D
import vn.map4d.map.core.OnMapReadyCallback
import vn.map4d.types.MFLocationCoordinate
import kotlin.random.Random

class BuildingActivity : AppCompatActivity(), OnMapReadyCallback {
  private lateinit var map4D: Map4D
  private val buildingList = mutableListOf<MFBuilding>()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_building)
    buildingMap?.getMapAsync(this)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = getString(R.string.building)
    setOnClickListener()
  }
  private fun setOnClickListener() {
    addBuilding?.setOnClickListener {
      addBuilding()
    }

    removeBuilding?.setOnClickListener {
      val building = buildingList.removeLastOrNull()
      building?.remove()
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

  private fun addBuilding() {
    if (this::map4D.isInitialized) {
      val location = map4D.cameraPosition.target
      val random = Random.nextDouble(-0.001, 0.001)
      val buidingOptions = MFBuildingOptions().location(MFLocationCoordinate(location.getLatitude() - random, location.getLongitude() - random))
        .name("Test Building ${buildingList.size + 1}")
        .model("https://sw-hcm-1.vinadata.vn/v1/AUTH_d0ecabcbdcd74f6aa6ac9a5da528eb78/sdk/models/5b21d9a5cd18d02d045a5e99")
        .texture("https://sw-hcm-1.vinadata.vn/v1/AUTH_d0ecabcbdcd74f6aa6ac9a5da528eb78/sdk/textures/0cb35e1610c34e55946a7839356d8f66.jpg")
      val building = map4D.addBuilding(buidingOptions)
      buildingList.add(building)
    }
  }

  override fun onMapReady(map4D: Map4D) {
    this.map4D = map4D
    map4D.isBuildingsEnabled = false
    map4D.enable3DMode(true)
    map4D.setOnUserBuildingClickListener {
      Toast.makeText(this, "Building:  ${it.name} is clicked", Toast.LENGTH_SHORT).show()
    }
  }
}