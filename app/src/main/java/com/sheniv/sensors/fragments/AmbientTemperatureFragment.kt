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
import com.sheniv.sensors.R
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentAmbientTemperatureBinding
import com.sheniv.sensors.extentions.beGone
import com.sheniv.sensors.extentions.bottomNavigationView
import com.sheniv.sensors.extentions.sensorManager

class AmbientTemperatureFragment : BaseFragment<FragmentAmbientTemperatureBinding>(),
    SensorEventListener {

    private var mTemperature: Sensor? = null

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAmbientTemperatureBinding.inflate(inflater, container, false)

    override fun FragmentAmbientTemperatureBinding.onBindView(savedInstanceState: Bundle?) {
        bottomNavigationView.beGone()
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            mTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        } else temperature.text = " No"
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0 != null) {
            binding.temperature.text = p0.values[0].toString()
            println("-------------------------${p0.values[0]}")
        } else {
            binding.temperature.text = "Нет датчика температуры"
            //println("-------------------------${p0.values[0]}")
        }
        println("-------------------------${p0!!.values[0]}")
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        println("-------------------------${p1}")
    }

    override fun onResume() {
        super.onResume()
        mTemperature?.also {
            sensorManager.registerListener(this, it , SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}