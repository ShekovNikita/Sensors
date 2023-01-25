package com.sheniv.sensors.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentHelpBinding
import com.sheniv.sensors.extentions.beVisible
import com.sheniv.sensors.extentions.bottomNavigationView

class HelpFragment : BaseFragment<FragmentHelpBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHelpBinding.inflate(inflater, container, false)

    override fun FragmentHelpBinding.onBindView(savedInstanceState: Bundle?) {
        bottomNavigationView.beVisible()
    }

}