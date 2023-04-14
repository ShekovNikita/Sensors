package com.sheniv.sensors.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import com.sheniv.sensors.R
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.PurchaseInfo
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sheniv.sensors.MainActivity
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentHelpBinding
import com.sheniv.sensors.extentions.*

class HelpFragment : BaseFragment<FragmentHelpBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHelpBinding.inflate(inflater, container, false)

    override fun FragmentHelpBinding.onBindView(savedInstanceState: Bundle?) {
        bottomNavigationView.beVisible()

        val confidencial = "https://shekovnikitacompany.blogspot.com/2023/01/sensors.html"
        val site_confidencial = Intent(Intent(Intent.ACTION_VIEW, Uri.parse(confidencial)))

        val play = "https://play.google.com/store/apps/dev?id=7801316179503456063"
        val play_market = Intent(Intent(Intent.ACTION_VIEW, Uri.parse(play)))

        btnConfidencial.setOnClickListener { startActivity(site_confidencial) }

        btnAds.setOnClickListener {
            (activity as MainActivity).showInterAd()
        }

        btnCooperation.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("shekovnikita8@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.cooperation_word))
            }
            startActivity(intent)
        }

        btnPlayMarket.setOnClickListener { startActivity(play_market) }

        btnInfo.setOnClickListener { dialogAboutAd() }

        btnDonation.setOnClickListener {
            bp?.purchase(requireActivity(), "donations")
        }
    }

    private fun dialogAboutAd() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle(getString(R.string.this_is_not_difficult))
            .setMessage(getString(R.string.about_this_is_not_difficult))
            .setPositiveButton("OK") { dialog, _ ->
                binding.btnAds.setBackgroundColor(Color.RED)
                dialog.dismiss()
            }.show()
    }
}