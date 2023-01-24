package com.sheniv.sensors.fragments

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sheniv.sensors.R
import com.sheniv.sensors.base.OneParameterBaseFragment
import com.sheniv.sensors.databinding.FragmentGravityBinding
import com.sheniv.sensors.extentions.sensorManager

class GravityFragment : OneParameterBaseFragment<FragmentGravityBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGravityBinding.inflate(inflater, container, false)

    override fun onSensorChanged(event: SensorEvent) {
        with(binding){
            axisX.text = String.format("%.3f", event.values[0])
            axisY.text = String.format("%.3f", event.values[1])
            axisZ.text = String.format("%.3f", event.values[2])
        }
    }

    override fun FragmentGravityBinding.onBindView(savedInstanceState: Bundle?) {
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
        } else {
            navController.popBackStack()
            navController.navigate(R.id.unfortunatelyFragment)
        }
    }

}