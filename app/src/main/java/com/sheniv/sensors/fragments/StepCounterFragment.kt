package com.sheniv.sensors.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sheniv.sensors.R
import com.sheniv.sensors.base.OneParameterBaseFragment
import com.sheniv.sensors.databinding.FragmentStepCounterBinding
import com.sheniv.sensors.extentions.sensorManager

class StepCounterFragment : OneParameterBaseFragment<FragmentStepCounterBinding>() {

    private var running = false
    private var previousTotalSteps = 0f
    private var totalSteps = -1

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentStepCounterBinding.inflate(inflater, container, false)

    override fun onResume() {
        super.onResume()
        if(ContextCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACTIVITY_RECOGNITION), 100)
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        binding.value.text = "${event.values[0]}"
    }

    override val currentSensor: Int
        get() = Sensor.TYPE_STEP_COUNTER

}