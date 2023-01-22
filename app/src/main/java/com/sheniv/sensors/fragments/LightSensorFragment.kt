package com.sheniv.sensors.fragments

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentLightSensorBinding
import com.sheniv.sensors.extentions.beGone
import com.sheniv.sensors.extentions.bottomNavigationView
import com.sheniv.sensors.extentions.sensorManager

class LightSensorFragment : BaseFragment<FragmentLightSensorBinding>(), SensorEventListener {

    private var mLight: Sensor? = null

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLightSensorBinding.inflate(inflater, container, false)

    override fun FragmentLightSensorBinding.onBindView(savedInstanceState: Bundle?) {
        bottomNavigationView.beGone()
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0 != null) {
            binding.light.text = p0.values[0].toString()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onResume() {
        super.onResume()
        mLight?.also {
            sensorManager.registerListener(this, it , SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}