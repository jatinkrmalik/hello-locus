package com.example.lap.ui

import android.os.Bundle
import android.os.Environment.getExternalStorageDirectory
import com.example.lap.BuildConfig
import com.example.lap.DummyData
import com.example.lap.R
import com.example.lap.base.BaseActivity
import com.example.lap.databinding.ActivityMapBinding
import com.example.lap.notifiers.Notify
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Polyline
import org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlay
import org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlayOptions
import org.osmdroid.views.overlay.simplefastpoint.SimplePointTheme
import java.io.File


class MapActivity : BaseActivity() {

    override fun getViewModel(): Nothing? = null
    override val dataBinding: Boolean = true
    override val layoutResource: Int = R.layout.activity_map
    private val binding: ActivityMapBinding by lazyBinding()

    override fun onNotificationReceived(data: Notify) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBindings() {
        binding.mapView
        binding.mapView.setTileSource(TileSourceFactory.MAPNIK)
        binding.mapView.setMultiTouchControls(true)
        binding.mapView.setBuiltInZoomControls(true)

        val mapController = binding.mapView.controller
        mapController.setZoom(13)
        val startPoint = GeoPoint(12.91, 77.6877)
        mapController.setCenter(startPoint)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun preDataBinding() {

//        Configuration.getInstance().setDebugMode(true);
//        Configuration.getInstance().setDebugMapView(true);
//        Configuration.getInstance().setDebugMapTileDownloader(true);
//        Configuration.getInstance().setDebugTileProviders(true);

        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
        Configuration.getInstance().osmdroidBasePath =
            File(getExternalStorageDirectory(), "osmdroid")
        Configuration.getInstance().osmdroidTileCache =
            File(getExternalStorageDirectory(), "osmdroid/tiles")
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        binding.mapView.onPause()
        super.onPause()
    }

    override fun initView() {
        val line = Polyline()   //see note below!
        line.setPoints(DummyData.data)
        binding.mapView.overlayManager.add(line)

        val pt = SimplePointTheme(DummyData.idata, true)

        val opt = SimpleFastPointOverlayOptions.getDefaultStyle()
            .setAlgorithm(SimpleFastPointOverlayOptions.RenderingAlgorithm.MAXIMUM_OPTIMIZATION)
            .setRadius(7f).setCellSize(15)

        binding.mapView.overlayManager.add(SimpleFastPointOverlay(pt, opt))

    }

}
