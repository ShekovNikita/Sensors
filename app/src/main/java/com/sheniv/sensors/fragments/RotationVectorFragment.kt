package com.sheniv.sensors.fragments

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sheniv.sensors.base.OneParameterBaseFragment
import com.sheniv.sensors.databinding.FragmentRotationVectorBinding

class RotationVectorFragment : OneParameterBaseFragment<FragmentRotationVectorBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRotationVectorBinding.inflate(inflater, container, false)

    override fun onSensorChanged(event: SensorEvent) {
        with(binding){
            axisX.text = String.format("%.3f", event.values[0])
            axisY.text = String.format("%.3f", event.values[1])
            axisZ.text = String.format("%.3f", event.values[2])
            axisQ.text = String.format("%.3f", event.values[3])
        }
    }

    override val currentSensor: Int
        get() = Sensor.TYPE_ROTATION_VECTOR

}