package com.sheniv.sensors.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.sheniv.sensors.R
import com.sheniv.sensors.adapters.AllSensorsAdapter
import com.sheniv.sensors.adapters.ChoiceSensor
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentMainBinding
import com.sheniv.sensors.extentions.beGone
import com.sheniv.sensors.extentions.beVisible
import com.sheniv.sensors.extentions.bottomNavigationView
import com.sheniv.sensors.models.AllSensors
import com.sheniv.sensors.models.SensorItem

class MainFragment : BaseFragment<FragmentMainBinding>(), ChoiceSensor {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(inflater, container, false)

    override fun FragmentMainBinding.onBindView(savedInstanceState: Bundle?) {
        bottomNavigationView.beVisible()
        recyclerType.layoutManager = GridLayoutManager(context, 3)
        recyclerType.adapter = AllSensorsAdapter(AllSensors().getAllSensors(), this@MainFragment)
    }

    override fun choiceSensor(sensorItem: SensorItem) {
        when (sensorItem.sensor_name) {
            R.string.sensor_light -> navController.navigate(R.id.lightSensorFragment)
            R.string.sensor_accelerometer -> navController.navigate(R.id.accelerometer)
            R.string.sensor_thermometer -> navController.navigate(R.id.ambientTemperatureFragment)
        }
    }
}