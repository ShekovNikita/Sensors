package com.sheniv.sensors.base

import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.viewbinding.ViewBinding
import com.sheniv.sensors.extentions.sensorManager

abstract class OneParameterBaseFragment<VIEW_BINDING : ViewBinding> : BaseFragment<VIEW_BINDING>(),
    SensorEventListener {

    var sensor: Sensor? = null

    override fun onResume() {
        super.onResume()
        sensor?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}