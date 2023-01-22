package com.sheniv.sensors.extentions

import android.hardware.SensorManager
import android.view.View

lateinit var sensorManager: SensorManager

fun View.beVisible() {
    this.visibility = View.VISIBLE
}

fun View.beGone() {
    this.visibility = View.GONE
}

fun View.beInvisible() {
    this.visibility = View.INVISIBLE
}