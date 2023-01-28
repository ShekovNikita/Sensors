package com.sheniv.sensors.extentions

import android.hardware.SensorManager
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

lateinit var sensorManager: SensorManager
lateinit var bottomNavigationView: BottomNavigationView
const val AD_UNIT_ID = "ca-app-pub-2440252298457934/2025554616"

fun View.beVisible() {
    this.visibility = View.VISIBLE
}

fun View.beGone() {
    this.visibility = View.GONE
}

fun View.beInvisible() {
    this.visibility = View.INVISIBLE
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}