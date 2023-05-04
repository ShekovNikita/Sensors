package com.sheniv.sensors.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import com.google.android.gms.ads.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.play.core.review.ReviewManagerFactory
import com.sheniv.sensors.MainActivity
import com.sheniv.sensors.R
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

        privacyPolicy.setOnClickListener {
            val privacy = "https://shekovnikitacompany.blogspot.com/2023/01/sensors.html"
            val site_privacy = Intent(Intent(Intent.ACTION_VIEW, Uri.parse(privacy)))
            startActivity(site_privacy)
        }

        cooperation.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("shekovnikita8@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.cooperation_word))
            }
            startActivity(intent)
        }

        otherApp.setOnClickListener {
            val play = "https://play.google.com/store/apps/dev?id=7801316179503456063"
            val play_market = Intent(Intent(Intent.ACTION_VIEW, Uri.parse(play)))
            startActivity(play_market)
        }

        donations.setOnClickListener {
            dialogAboutAd()
        }

        feedback.setOnClickListener { reviewOnPlayMarket() }
    }

    private fun reviewOnPlayMarket(){
        val reviewManger = ReviewManagerFactory.create(requireActivity())
        reviewManger.requestReviewFlow().addOnCompleteListener{
            if (it.isSuccessful){
                reviewManger.launchReviewFlow(requireActivity(), it.result)
            }
        }
    }

    private fun dialogAboutAd() {

        val dialogBinding = layoutInflater.inflate(R.layout.dialog_ads_donation, null)

        val width = (ViewGroup.LayoutParams.MATCH_PARENT).toInt()
        val height = ViewGroup.LayoutParams.WRAP_CONTENT

        val alertDialog = MaterialAlertDialogBuilder(requireActivity())
            .setView(dialogBinding)
            .create()
        alertDialog.show()
        alertDialog.window?.setDimAmount(0.8f)
        alertDialog.window?.setLayout(width, height)

        val btn_ads = dialogBinding.findViewById<Button>(R.id.btn_ads)
        val btn_donation = dialogBinding.findViewById<Button>(R.id.btn_donation)
        val btn_exit = dialogBinding.findViewById<ImageView>(R.id.btn_close_dialog)

        btn_exit.setOnClickListener { alertDialog.dismiss() }
        btn_ads.setOnClickListener { (activity as MainActivity).showInterAd() }
        btn_donation.setOnClickListener { bp?.purchase(requireActivity(), "donations") }

        /*MaterialAlertDialogBuilder(requireActivity())
            .setTitle(getString(R.string.this_is_not_difficult))
            .setMessage(getString(R.string.about_this_is_not_difficult))
            .setPositiveButton("Пожертвовать") { dialog, _ ->
                bp?.purchase(requireActivity(), "donations")
                //dialog.dismiss()
            }
            .setNegativeButton("Посмотреть рекламу") { _, _ ->
                (activity as MainActivity).showInterAd()
            }
            .show()*/
    }
}