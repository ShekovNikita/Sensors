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
        recyclerType.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerType.adapter = AllSensorsAdapter(AllSensors().getAllSensors(), this@MainFragment)
    }

    override fun choiceSensor(sensorItem: SensorItem) {
        when (sensorItem.sensor_name) {
            R.string.sensor_light -> navController.navigate(R.id.lightSensorFragment)
            R.string.sensor_accelerometer -> navController.navigate(R.id.accelerometer)
            R.string.sensor_thermometer -> navController.navigate(R.id.ambientTemperatureFragment)
            R.string.sensor_gravity -> navController.navigate(R.id.gravityFragment)
            R.string.humidity -> navController.navigate(R.id.relativeHumidityFragment)
            R.string.linear_acceleration -> navController.navigate(R.id.linearAccelerationFragment)
            R.string.gyroscope -> navController.navigate(R.id.gyroscopeFragment)
            R.string.magnetic_field -> navController.navigate(R.id.magneticFieldFragment)
            R.string.orientation -> navController.navigate(R.id.orientationFragment)
            R.string.pressure -> navController.navigate(R.id.pressureFragment)
            R.string.proximity -> navController.navigate(R.id.proximityFragment)
            R.string.rotation_vector -> navController.navigate(R.id.rotationVectorFragment)
            R.string.step_counter -> navController.navigate(R.id.stepCounterFragment)
            R.string.compass -> navController.navigate(R.id.compassFragment)
        }
    }
}