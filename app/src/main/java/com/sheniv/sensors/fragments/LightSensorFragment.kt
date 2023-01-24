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
import com.sheniv.sensors.databinding.FragmentLightSensorBinding
import com.sheniv.sensors.extentions.beGone
import com.sheniv.sensors.extentions.bottomNavigationView
import com.sheniv.sensors.extentions.sensorManager

class LightSensorFragment : OneParameterBaseFragment<FragmentLightSensorBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLightSensorBinding.inflate(inflater, container, false)

    override fun FragmentLightSensorBinding.onBindView(savedInstanceState: Bundle?) {
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        } else {
            navController.popBackStack()
            navController.navigate(R.id.unfortunatelyFragment)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null)
            binding.light.text = event.values[0].toString()
    }
}