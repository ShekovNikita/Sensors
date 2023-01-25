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

    override val currentSensor: Int
        get() = Sensor.TYPE_LIGHT

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLightSensorBinding.inflate(inflater, container, false)

    override fun onSensorChanged(event: SensorEvent) {
            binding.value.text = event.values[0].toString()
    }
}