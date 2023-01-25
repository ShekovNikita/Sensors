package com.sheniv.sensors.fragments

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
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
        val values = event.values[0]
        val color = sensor?.maximumRange?.div(256f)
        val colorSet = (values/ color!!).toInt()

        with(binding){
            circularProgressBar.apply {
                setProgressWithAnimation(values)
            }
            layout.setBackgroundColor(Color.argb(255, colorSet, colorSet, 0))
            value.setTextColor(Color.argb(255, 255, 255 - colorSet, 200))
            value.text = values.toInt().toString()
        }
    }
}