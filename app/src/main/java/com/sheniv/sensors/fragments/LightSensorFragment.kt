package com.sheniv.sensors.fragments

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.google.android.gms.common.util.Hex
import com.sheniv.sensors.R
import com.sheniv.sensors.base.OneParameterBaseFragment
import com.sheniv.sensors.databinding.FragmentLightSensorBinding

class LightSensorFragment : OneParameterBaseFragment<FragmentLightSensorBinding>() {

    override val currentSensor: Int
        get() = Sensor.TYPE_LIGHT

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLightSensorBinding.inflate(inflater, container, false)

    override fun FragmentLightSensorBinding.onBindView(savedInstanceState: Bundle?) {
        super.checkingSensor(currentSensor)
        val sensorMax = sensor?.maximumRange
        if (sensorMax != null){
            circularProgressBar.progressMax = sensorMax
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSensorChanged(event: SensorEvent) {
        val values = event.values[0].toInt()

        with(binding){
            when (values.toInt()){
                in 0 .. 500 -> { imageLamp.setImageResource(R.drawable.light_low) }
                in 500 .. 1000 -> { imageLamp.setImageResource(R.drawable.light_good) }
                in 1000 .. 30000 -> { imageLamp.setImageResource(R.drawable.light_high) }
            }
            if (values < 1000) {
                val g = 209 - values / 26
                val b = 115 - values / 9

                circularProgressBar.progressBarColor = Color.argb(160, 255, g, b)
            }
            value.text = "$values lx"
        }
    }
}