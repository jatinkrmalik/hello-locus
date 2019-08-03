package com.example.lap

import org.osmdroid.api.IGeoPoint
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.simplefastpoint.LabelledGeoPoint

object DummyData {
    val data = mutableListOf(
        GeoPoint(12.9078, 77.6886),
        GeoPoint(12.9075, 77.6893),
        GeoPoint(12.9078, 77.6889),
        GeoPoint(12.9075, 77.6894),
        GeoPoint(12.9096, 77.6879),
        GeoPoint(12.91, 77.6877),
        GeoPoint(12.9094, 77.6885),
        GeoPoint(12.9101, 77.6877),
        GeoPoint(12.9099, 77.6867),
        GeoPoint(12.9104, 77.6876)
    )
    val idata = mutableListOf<IGeoPoint>(
        LabelledGeoPoint(12.9078, 77.6886),
        LabelledGeoPoint(12.9075, 77.6893),
        LabelledGeoPoint(12.9078, 77.6889),
        LabelledGeoPoint(12.9075, 77.6894),
        LabelledGeoPoint(12.9096, 77.6879),
        LabelledGeoPoint(12.91, 77.6877),
        LabelledGeoPoint(12.9094, 77.6885),
        LabelledGeoPoint(12.9101, 77.6877),
        LabelledGeoPoint(12.9099, 77.6867),
        LabelledGeoPoint(12.9104, 77.6876)
    )
}