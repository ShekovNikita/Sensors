package com.sheniv.sensors.fragments

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sheniv.sensors.base.OneParameterBaseFragment
import com.sheniv.sensors.databinding.FragmentProximityBinding

class ProximityFragment : OneParameterBaseFragment<FragmentProximityBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProximityBinding.inflate(inflater, container, false)

    override fun onSensorChanged(event: SensorEvent) {
        with(binding) {
            if (event.values[0] >= 5) {
                value.text = "> 5"
                layout.setBackgroundColor(Color.argb(255, 255, 255, 255))
                value.setTextColor(Color.argb(255,0,0,0))
                materialTextView.setTextColor(Color.argb(255,0,0,0))
            } else {
                value.text = "< 5"
                layout.setBackgroundColor(Color.argb(255, 0, 0, 0))
                value.setTextColor(Color.argb(255,255,255,255))
                materialTextView.setTextColor(Color.argb(255,255,255,255))
            }
        }
    }

    override val currentSensor: Int
        get() = Sensor.TYPE_PROXIMITY
}