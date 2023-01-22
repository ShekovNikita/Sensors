package com.sheniv.sensors.fragments

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.sheniv.sensors.R
import com.sheniv.sensors.adapters.SensorAdapter
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentMainBinding
import com.sheniv.sensors.extentions.sensorManager

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(inflater, container, false)

    override fun FragmentMainBinding.onBindView(savedInstanceState: Bundle?) {

    }

}