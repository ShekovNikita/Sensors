package com.sheniv.sensors.fragments

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentAccelerometerBinding
import com.sheniv.sensors.extentions.sensorManager

class AccelerometerFragment : BaseFragment<FragmentAccelerometerBinding>(), SensorEventListener {

    private var mLight: Sensor? = null

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAccelerometerBinding.inflate(inflater, container, false)

    override fun FragmentAccelerometerBinding.onBindView(savedInstanceState: Bundle?) {
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
        mLight?.also { light ->
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}