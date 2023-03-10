package com.sheniv.sensors.fragments

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentCompassBinding
import com.sheniv.sensors.extentions.sensorManager


class CompassFragment : BaseFragment<FragmentCompassBinding>() {

    private var gravity = FloatArray(3)
    private var geometric = FloatArray(3)

    private var currentAzimuth = 0f
    private var azimuth = 0f

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCompassBinding.inflate(inflater, container, false)

    override fun FragmentCompassBinding.onBindView(savedInstanceState: Bundle?) {
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sensor2 = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val sListener = object : SensorEventListener {
            val alpha = 0.97f
            override fun onSensorChanged(event: SensorEvent?) {
                when (event?.sensor?.type) {
                    Sensor.TYPE_ACCELEROMETER -> {
                        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0]
                        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1]
                        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2]
                    }
                    Sensor.TYPE_MAGNETIC_FIELD -> {
                        geometric[0] = alpha * geometric[0] + (1 - alpha) * event.values[0]
                        geometric[1] = alpha * geometric[1] + (1 - alpha) * event.values[1]
                        geometric[2] = alpha * geometric[2] + (1 - alpha) * event.values[2]
                    }
                }
                val r = FloatArray(9)
                val i = FloatArray(9)
                var success = SensorManager.getRotationMatrix(r, i, gravity, geometric)

                if (success){
                    val orientation = FloatArray(3)
                    SensorManager.getOrientation(r, orientation)
                    azimuth = Math.toDegrees(orientation[0].toDouble()).toFloat()
                    azimuth = (azimuth + 360) % 360

                    val animation = RotateAnimation(-currentAzimuth, -azimuth, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
                    currentAzimuth = azimuth

                    animation.duration = 500
                    animation.repeatCount = 0
                    animation.fillAfter = true

                    imageCompass.startAnimation(animation)
                    value.text = "${azimuth.toInt()}??"
                }
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

            }

        }
        sensorManager.registerListener(sListener, sensor, SensorManager.SENSOR_DELAY_GAME)
        sensorManager.registerListener(sListener, sensor2, SensorManager.SENSOR_DELAY_GAME)
    }
}