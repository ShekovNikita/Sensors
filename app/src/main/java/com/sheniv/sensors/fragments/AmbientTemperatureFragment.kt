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
import com.sheniv.sensors.base.OneParameterBaseFragment
import com.sheniv.sensors.databinding.FragmentAmbientTemperatureBinding
import com.sheniv.sensors.extentions.beGone
import com.sheniv.sensors.extentions.bottomNavigationView
import com.sheniv.sensors.extentions.sensorManager

class AmbientTemperatureFragment : OneParameterBaseFragment<FragmentAmbientTemperatureBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAmbientTemperatureBinding.inflate(inflater, container, false)

    override fun FragmentAmbientTemperatureBinding.onBindView(savedInstanceState: Bundle?) {
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        } else {
            navController.popBackStack()
            navController.navigate(R.id.unfortunatelyFragment)
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0 != null) {
            binding.temperature.text = p0.values[0].toString()
        }
    }
}