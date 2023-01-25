package com.sheniv.sensors.fragments

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.sheniv.sensors.base.OneParameterBaseFragment
import com.sheniv.sensors.databinding.FragmentCompassBinding
import kotlin.math.roundToInt


class CompassFragment : OneParameterBaseFragment<FragmentCompassBinding>() {

    private var degreeStart = 0f

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCompassBinding.inflate(inflater, container, false)

    override fun onSensorChanged(event: SensorEvent) {
        val degree = event.values[0].roundToInt().toFloat()
        binding.value.text = degree.toString()
        // rotation animation - reverse turn degree degrees
        val ra = RotateAnimation(
            degreeStart,
            -degree,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        // set the compass animation after the end of the reservation status
        ra.fillAfter = true
        // set how long the animation for the compass image will take place
        ra.duration = 210
        // Start animation of compass image
        binding.imageCompass.startAnimation(ra)
        degreeStart = -degree
    }

    override val currentSensor: Int
        get() = Sensor.TYPE_ORIENTATION

}