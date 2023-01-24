package com.sheniv.sensors.models

import android.provider.Settings.Global.getString
import com.sheniv.sensors.R

class AllSensors {

    private val sensorLight = SensorItem(R.drawable.ic_baseline_lightbulb_24, R.string.sensor_light)
    private val sensorAccelerometer = SensorItem(R.drawable.ic_baseline_360_24, R.string.sensor_accelerometer)
    private val sensorThermometer = SensorItem(R.drawable.ic_baseline_device_thermostat_24, R.string.sensor_thermometer)
    private val sensorGravity = SensorItem(R.drawable.ic_baseline_device_thermostat_24, R.string.sensor_gravity)

    fun getAllSensors(): List<SensorItem> {
        val listSensor = arrayListOf<SensorItem>()
        listSensor.add(sensorLight)
        listSensor.add(sensorAccelerometer)
        listSensor.add(sensorThermometer)
        listSensor.add(sensorGravity)

        return listSensor
    }
}