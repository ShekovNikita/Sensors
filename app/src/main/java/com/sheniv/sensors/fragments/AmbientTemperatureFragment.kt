package com.sheniv.sensors.fragments

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sheniv.sensors.base.OneParameterBaseFragment
import com.sheniv.sensors.databinding.FragmentAmbientTemperatureBinding

class AmbientTemperatureFragment : OneParameterBaseFragment<FragmentAmbientTemperatureBinding>() {

    override val currentSensor: Int
        get() = Sensor.TYPE_AMBIENT_TEMPERATURE

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAmbientTemperatureBinding.inflate(inflater, container, false)

    override fun onSensorChanged(value: SensorEvent) {
            binding.value.text = value.values[0].toString()
    }
}