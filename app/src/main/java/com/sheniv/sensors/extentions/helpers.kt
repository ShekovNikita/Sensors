package com.sheniv.sensors.extentions

import android.hardware.SensorManager
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

lateinit var sensorManager: SensorManager
lateinit var bottomNavigationView: BottomNavigationView

fun View.beVisible() {
    this.visibility = View.VISIBLE
}

fun View.beGone() {
    this.visibility = View.GONE
}

fun View.beInvisible() {
    this.visibility = View.INVISIBLE
}