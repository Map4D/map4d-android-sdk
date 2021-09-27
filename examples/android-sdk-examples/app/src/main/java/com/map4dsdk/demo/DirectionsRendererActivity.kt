package com.map4dsdk.demo

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_directions_renderer.*
import vn.map4d.map.annotations.MFDirectionsRenderer
import vn.map4d.map.annotations.MFDirectionsRendererOptions
import vn.map4d.map.camera.MFCameraUpdateFactory
import vn.map4d.map.core.Map4D
import vn.map4d.map.core.OnMapReadyCallback
import vn.map4d.types.MFLocationCoordinate
import java.util.*
import kotlin.random.Random

class DirectionsRendererActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map4D: Map4D

    private lateinit var directionsRenderer: MFDirectionsRenderer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_directions_renderer)
        map?.getMapAsync(this)
        supportActionBar?.title = getString(R.string.directionsRenderer)
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
        add?.setOnClickListener {
            addDirectionsRenderer()
        }
        remove?.setOnClickListener {
            directionsRenderer?.remove()
        }
    }

    private fun addDirectionsRenderer() {
        if (this::map4D.isInitialized) {
            val directionsRendererOptions = MFDirectionsRendererOptions()

            val paths: MutableList<List<MFLocationCoordinate>> = ArrayList()
            val path1: MutableList<MFLocationCoordinate> = ArrayList()
            path1.add(MFLocationCoordinate(16.070508, 108.221204))
            path1.add(MFLocationCoordinate(16.071449, 108.221124))
            path1.add(MFLocationCoordinate(16.071606, 108.222706))

            val path2: MutableList<MFLocationCoordinate> = ArrayList()
            path2.add(MFLocationCoordinate(16.070508, 108.221204))
            path2.add(MFLocationCoordinate(16.070369, 108.222870))
            path2.add(MFLocationCoordinate(16.071606, 108.222706))

            paths.add(path1)
            paths.add(path2)

            directionsRendererOptions.paths(paths)
            directionsRendererOptions.activeStrokeColor(resources.getColor(R.color.colorActiveStroke))
            directionsRendererOptions.inactiveStrokeColor(resources.getColor(R.color.colorInactiveStroke))
            directionsRendererOptions.activeOutlineColor(Color.BLUE)
            directionsRendererOptions.inactiveOutlineColor(Color.BLACK)
            directionsRendererOptions.width(10f)
            directionsRendererOptions.startLocation(MFLocationCoordinate(16.070526, 108.220990))
            directionsRendererOptions.startLabel("Start")
            directionsRendererOptions.endLocation(MFLocationCoordinate(16.071523, 108.222960))
            directionsRendererOptions.endLabel("End")
            directionsRenderer = map4D.addDirectionsRenderer(directionsRendererOptions)
        }
    }

    override fun onMapReady(map4D: Map4D) {
        this.map4D = map4D
        map4D.setOnDirectionsClickListener { directionsRenderer, index ->
            Toast.makeText(this, "Route index: $index is clicked", Toast.LENGTH_SHORT).show()
        }
        map4D.moveCamera(MFCameraUpdateFactory.newCoordinateZoom(
                MFLocationCoordinate(16.071021, 108.222054),
                18.0))
    }
    override fun onDestroy() {
        map?.onDestroy()
        super.onDestroy()
    }
}