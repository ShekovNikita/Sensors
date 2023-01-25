package com.sheniv.sensors.fragments

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sheniv.sensors.base.OneParameterBaseFragment
import com.sheniv.sensors.databinding.FragmentRelativeHumidityBinding

class RelativeHumidityFragment : OneParameterBaseFragment<FragmentRelativeHumidityBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRelativeHumidityBinding.inflate(inflater, container, false)

    override fun onSensorChanged(p0: SensorEvent) {
        binding.value.text = p0.values.toString()
    }

    override val currentSensor: Int
        get() = Sensor.TYPE_RELATIVE_HUMIDITY

}