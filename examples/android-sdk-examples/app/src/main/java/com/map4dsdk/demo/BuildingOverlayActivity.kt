package com.map4dsdk.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_building_overlay.*
import kotlinx.android.synthetic.main.activity_tile_overlay.*
import org.json.JSONException
import org.json.JSONObject
import vn.map4d.map.core.Map4D
import vn.map4d.map.core.OnMapReadyCallback
import vn.map4d.map.overlays.MFBuildingData
import vn.map4d.map.overlays.MFBuildingOverlayOptions
import vn.map4d.map.overlays.MFBuildingProvider
import vn.map4d.map.overlays.MFUrlBuildingProvider
import vn.map4d.types.MFLocationCoordinate

class BuildingOverlayActivity : AppCompatActivity(), OnMapReadyCallback {
  private lateinit var map4D: Map4D
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_building_overlay)
    buildingOverlayMap?.getMapAsync(this)
    supportActionBar?.title = getString(R.string.buildingOverlay)
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

  private val buildingProvider: MFBuildingProvider = object : MFUrlBuildingProvider() {
    override fun getBuildingUrl(x: Int, y: Int, zoom: Int): String {
      return "https://poi-random.herokuapp.com/poi/$zoom/$x/$y"
    }

    override fun parserBuildingData(data: String): List<MFBuildingData> {
      val obj: JSONObject
      val buildings: MutableList<MFBuildingData> = ArrayList()
      try {
        obj = JSONObject(data)
        val pois = obj.getJSONArray("pois")
        for (i in 0 until pois.length()) {
          val poi = pois.getJSONObject(i)
          val position = poi.getJSONObject("position")
          val lat = position.getDouble("lat")
          val lng = position.getDouble("lng")
          val id = poi.getString("id")
          val model = "model-Url"
          val texture = "texture-Url"
          val location = MFLocationCoordinate(lat, lng)
          val buildingData = MFBuildingData(id, location, "https://sw-hcm-1.vinadata.vn/v1/AUTH_d0ecabcbdcd74f6aa6ac9a5da528eb78/sdk/models/5b21d9a5cd18d02d045a5e99", "https://sw-hcm-1.vinadata.vn/v1/AUTH_d0ecabcbdcd74f6aa6ac9a5da528eb78/sdk/textures/0cb35e1610c34e55946a7839356d8f66.jpg")
          buildings.add(buildingData)
        }
      } catch (e: JSONException) {
        e.printStackTrace()
      }
      return buildings
    }
  }

  override fun onMapReady(map4D: Map4D) {
    this.map4D = map4D
    map4D.enable3DMode(true)
    map4D.addBuildingOverlay(MFBuildingOverlayOptions().buildingProvider(buildingProvider).prefixId("buildingOverlay-"))
  }

  override fun onDestroy() {
    buildingOverlayMap?.onDestroy()
    super.onDestroy()
  }
}