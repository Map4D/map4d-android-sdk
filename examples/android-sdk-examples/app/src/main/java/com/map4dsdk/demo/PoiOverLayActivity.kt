package com.map4dsdk.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_poi_over_lay.*
import kotlinx.android.synthetic.main.activity_tile_overlay.*
import org.json.JSONException
import org.json.JSONObject
import vn.map4d.map.core.Map4D
import vn.map4d.map.core.OnMapReadyCallback
import vn.map4d.map.overlays.*
import vn.map4d.types.MFLocationCoordinate

class PoiOverLayActivity : AppCompatActivity(), OnMapReadyCallback {
  private lateinit var map4D: Map4D
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_poi_over_lay)
    supportActionBar?.title = getString(R.string.poiOverlay)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    poiOverlayMap?.getMapAsync(this)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id: Int = item.itemId
    if (id == android.R.id.home) {
      onBackPressed()
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  private val poiProvider =  object: MFUrlPOIProvider() {
    override fun getPOIUrl(x: Int, y: Int, zoom: Int, _3dMode: Boolean): String {
      return "https://poi-random.herokuapp.com/poi/$zoom/$x/$y"
    }

    override fun parserPOIData(data: String): MutableList<MFPOIData> {
      val obj: JSONObject
      val list: MutableList<MFPOIData> = ArrayList()
      try {
        obj = JSONObject(data)
        val pois = obj.getJSONArray("pois")
        for (i in 0 until pois.length()) {
          val poi = pois.getJSONObject(i)
          val id = poi.getString("id")
          val title = poi.getString("title")
          val position = poi.getJSONObject("position")
          val lat = position.getDouble("lat")
          val lng = position.getDouble("lng")
          val poiData = MFPOIData(id, title, MFLocationCoordinate(lat, lng))
          list.add(poiData)
        }
      } catch (e: JSONException) {
        e.printStackTrace()
      }
      return list
    }
  }

  override fun onMapReady(map4D: Map4D) {
    this.map4D = map4D
    map4D?.isPOIsEnabled = false
    var poiOverlay = map4D.addPOIOverlay(MFPOIOverlayOptions().poiProvider(poiProvider).prefixId("poiOverlay-"))
  }

  override fun onDestroy() {
    poiOverlayMap?.onDestroy()
    super.onDestroy()
  }
}