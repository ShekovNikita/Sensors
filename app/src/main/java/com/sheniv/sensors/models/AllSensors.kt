package com.sheniv.sensors.models

import com.sheniv.sensors.R

class AllSensors {

    private val sensorLight = SensorItem(R.drawable.light_icon, R.string.sensor_light)
    private val sensorAccelerometer = SensorItem(R.drawable.gravity, R.string.sensor_accelerometer)
    private val sensorAmbientTemperature = SensorItem(R.drawable.temperature, R.string.sensor_thermometer)
    private val sensorGravity = SensorItem(R.drawable.accelerometer, R.string.sensor_gravity)
    private val sensorRelativeHumidity = SensorItem(R.drawable.humidity, R.string.humidity)
    private val sensorLinearAcceleration = SensorItem(R.drawable.linear_acceleration, R.string.linear_acceleration)
    private val sensorGyroscope = SensorItem(R.drawable.gyroscope, R.string.gyroscope)
    private val sensorMagneticField = SensorItem(R.drawable.magnetic_field, R.string.magnetic_field)
    private val sensorOrientation = SensorItem(R.drawable.orientation, R.string.orientation)
    private val sensorPressure = SensorItem(R.drawable.pressure, R.string.pressure)
    private val sensorProximity = SensorItem(R.drawable.proximity, R.string.proximity)
    private val sensorRotationVector = SensorItem(R.drawable.rotation_vector, R.string.rotation_vector)
    private val sensorStepCounter = SensorItem(R.drawable.step_counter, R.string.step_counter)
    private val compass = SensorItem(R.drawable.compass, R.string.compass)

    fun getAllSensors(): List<SensorItem> {
        val listSensor = arrayListOf<SensorItem>()
        listSensor.add(sensorLight)
        listSensor.add(sensorAccelerometer)
        listSensor.add(sensorAmbientTemperature)
        listSensor.add(sensorGravity)
        listSensor.add(sensorRelativeHumidity)
        listSensor.add(sensorLinearAcceleration)
        listSensor.add(sensorGyroscope)
        listSensor.add(sensorMagneticField)
        listSensor.add(sensorOrientation)
        listSensor.add(sensorPressure)
        listSensor.add(sensorProximity)
        listSensor.add(sensorRotationVector)
        listSensor.add(sensorStepCounter)
        listSensor.add(compass)

        return listSensor
    }
}