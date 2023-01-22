package com.sheniv.sensors.fragments

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentAccelerometerBinding
import com.sheniv.sensors.extentions.beGone
import com.sheniv.sensors.extentions.bottomNavigationView
import com.sheniv.sensors.extentions.sensorManager

class AccelerometerFragment : BaseFragment<FragmentAccelerometerBinding>(), SensorEventListener {

    private var mAccelerometer: Sensor? = null

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAccelerometerBinding.inflate(inflater, container, false)

    override fun FragmentAccelerometerBinding.onBindView(savedInstanceState: Bundle?) {
        bottomNavigationView.beGone()
        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onSensorChanged(p0: SensorEvent) {
        with(binding){
            axisX.text = p0.values[0].toString()
            axisY.text = p0.values[1].toString()
            axisZ.text = p0.values[2].toString()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onResume() {
        super.onResume()
        mAccelerometer?.also {
            sensorManager.registerListener(this, it , SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}