package com.sheniv.sensors.fragments

import android.hardware.Sensor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sheniv.sensors.adapters.SensorAdapter
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentAllSensorsBinding
import com.sheniv.sensors.extentions.beVisible
import com.sheniv.sensors.extentions.bottomNavigationView
import com.sheniv.sensors.extentions.sensorManager

class AllSensorsFragment : BaseFragment<FragmentAllSensorsBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAllSensorsBinding.inflate(inflater, container, false)

    override fun FragmentAllSensorsBinding.onBindView(savedInstanceState: Bundle?) {
        bottomNavigationView.beVisible()
        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

        recyclerSensor.adapter = SensorAdapter(deviceSensors)
    }


}