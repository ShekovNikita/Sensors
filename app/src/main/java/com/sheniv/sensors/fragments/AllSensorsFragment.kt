package com.sheniv.sensors.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sheniv.sensors.adapters.SearchInGoogle
import com.sheniv.sensors.adapters.SensorAdapter
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentAllSensorsBinding
import com.sheniv.sensors.extentions.beVisible
import com.sheniv.sensors.extentions.bottomNavigationView

class AllSensorsFragment : BaseFragment<FragmentAllSensorsBinding>(), SearchInGoogle {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAllSensorsBinding.inflate(inflater, container, false)

    override fun FragmentAllSensorsBinding.onBindView(savedInstanceState: Bundle?) {
        bottomNavigationView.beVisible()

        recyclerSensor.adapter = SensorAdapter(this@AllSensorsFragment)

    }

    override fun search(name: String, vendor: String) {
        val uri = Uri.parse("https://www.google.com/search?q=$name+$vendor")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}