package com.sheniv.sensors.fragments

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sheniv.sensors.base.OneParameterBaseFragment
import com.sheniv.sensors.databinding.FragmentProximityBinding

class ProximityFragment : OneParameterBaseFragment<FragmentProximityBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProximityBinding.inflate(inflater, container, false)

    override fun onSensorChanged(event: SensorEvent) {
        binding.value.text = event.values[0].toString()
    }

    override val currentSensor: Int
        get() = Sensor.TYPE_PROXIMITY
}