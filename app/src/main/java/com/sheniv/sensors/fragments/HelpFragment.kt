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
import com.sheniv.sensors.base.BaseFragment
import com.sheniv.sensors.databinding.FragmentHelpBinding
import com.sheniv.sensors.extentions.AD_UNIT_ID
import com.sheniv.sensors.extentions.beVisible
import com.sheniv.sensors.extentions.bottomNavigationView
import com.sheniv.sensors.extentions.showToast

class HelpFragment : BaseFragment<FragmentHelpBinding>(), BillingProcessor.IBillingHandler {

    private var mIsLoading = false
    private var mRewardedAd: RewardedAd? = null
    private var bp: BillingProcessor? = null
    private val BILLING_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgB6sQu/cyeLXLEj1iPP/xtUtugV6MtZuWUfx1OE6eS9w4JQ4abTh5Ip5Vvggn9wuVCgeDcHj1koKvs+w38pCEzz61GpXZ+v8St+rIzPZm4zbYPVU1itFO/68R/w/R/ttEFOg+EaKQsBCpuPwG5WpZiF6nGCJF8+P9n+5wrsJKt1hlkD8a11HBZMPpRXmrvpsCyiQiBwgjWGtqj3jvavHuaMZuXpouTn0YsqdswgQplkneyqqiTqwW1p7xNPb/BG3fH8//FNv49Yu62orgvgY58UgaMWisqF4CKW8mOhcQ0JzBjwQQW5B5Dd+jDqumRzumkMUDHWA923DVniIxgOoeQIDAQAB"

    override fun onResume() {
        super.onResume()
        loadInterAd()
    }

    override fun onDestroyView() {
        bp?.release()
        super.onDestroyView()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHelpBinding.inflate(inflater, container, false)

    override fun FragmentHelpBinding.onBindView(savedInstanceState: Bundle?) {
        bottomNavigationView.beVisible()

        val confidencial = "https://shekovnikitacompany.blogspot.com/2023/01/sensors.html"
        val site_confidencial = Intent(Intent(Intent.ACTION_VIEW, Uri.parse(confidencial)))

        initAdMob()
        donationsInit()
        val play = "https://play.google.com/store/apps/dev?id=7801316179503456063"
        val play_market = Intent(Intent(Intent.ACTION_VIEW, Uri.parse(play)))

        btnConfidencial.setOnClickListener { startActivity(site_confidencial) }

        btnAds.setOnClickListener {
            showInterAd()
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
            bp?.consumePurchaseAsync("donations", object : BillingProcessor.IPurchasesResponseListener{
                override fun onPurchasesSuccess() {
                }

                override fun onPurchasesError() {
                }
            })
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

    private fun initAdMob() {
        MobileAds.initialize(requireActivity())
    }

    private fun loadInterAd() {
        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(
            requireActivity(),
            AD_UNIT_ID,
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mIsLoading = false
                    mRewardedAd = null
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    mRewardedAd = rewardedAd
                    mIsLoading = false
                }
            }
        )
    }

    private fun showInterAd() {
        if (mRewardedAd != null) {
            mRewardedAd?.fullScreenContentCallback =
                object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        // Don't forget to set the ad reference to null so you
                        // don't show the ad a second time.
                        mRewardedAd = null
                        loadInterAd()
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        // Don't forget to set the ad reference to null so you
                        // don't show the ad a second time.
                        mRewardedAd = null
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when ad is dismissed.
                        showContent()
                        binding.btnAds.background = null
                    }
                }

            mRewardedAd?.show(
                requireActivity()
            ) {
                showContent()
            }
        }
    }

    private fun showContent() {
        showToast(getString(R.string.thank_you_for_watching_ads))
    }

    private fun donationsInit() {
        bp = BillingProcessor.newBillingProcessor(requireActivity(), BILLING_KEY, this)
        bp?.initialize()
    }

    override fun onProductPurchased(productId: String, details: PurchaseInfo?) {
    }

    override fun onPurchaseHistoryRestored() {
    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {
        showToast("Error ${error?.localizedMessage}")
    }

    override fun onBillingInitialized() {
    }

}