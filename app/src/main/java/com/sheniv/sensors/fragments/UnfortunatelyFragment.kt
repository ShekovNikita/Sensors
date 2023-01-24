package com.sheniv.sensors.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sheniv.sensors.R
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentUnfortunatelyBinding
import com.sheniv.sensors.extentions.beGone
import com.sheniv.sensors.extentions.bottomNavigationView

class UnfortunatelyFragment : BaseFragment<FragmentUnfortunatelyBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUnfortunatelyBinding.inflate(inflater, container, false)

    override fun FragmentUnfortunatelyBinding.onBindView(savedInstanceState: Bundle?) {
        bottomNavigationView.beGone()
    }

}