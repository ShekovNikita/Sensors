package com.sheniv.sensors.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sheniv.sensors.R
import com.sheniv.sensors.adapters.AllSensorsAdapter
import com.sheniv.sensors.adapters.ChoiceSensor
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentMainBinding
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

        val nav = fun(navigate: Int) = navController.navigate(navigate)

        when (sensorItem.sensor_name) {
            R.string.sensor_light -> nav(R.id.lightSensorFragment)
            R.string.sensor_accelerometer -> nav(R.id.accelerometer)
            R.string.sensor_thermometer -> nav(R.id.ambientTemperatureFragment)
            R.string.sensor_gravity -> nav(R.id.gravityFragment)
            R.string.humidity -> nav(R.id.relativeHumidityFragment)
            R.string.linear_acceleration -> nav(R.id.linearAccelerationFragment)
            R.string.gyroscope -> nav(R.id.gyroscopeFragment)
            R.string.magnetic_field -> nav(R.id.magneticFieldFragment)
            R.string.orientation -> nav(R.id.orientationFragment)
            R.string.pressure -> nav(R.id.pressureFragment)
            R.string.proximity -> nav(R.id.proximityFragment)
            R.string.rotation_vector -> nav(R.id.rotationVectorFragment)
            R.string.step_counter -> nav(R.id.stepCounterFragment)
            R.string.compass -> nav(R.id.compassFragment)
            R.string.level -> nav(R.id.levelFragment)
        }
    }
}