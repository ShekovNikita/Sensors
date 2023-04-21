package com.sheniv.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.PurchaseInfo
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.sheniv.sensors.databinding.ActivityMainBinding
import com.sheniv.sensors.extentions.*
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

class MainActivity : AppCompatActivity(), BillingProcessor.IBillingHandler {

    private lateinit var binding: ActivityMainBinding
    private var appUpdateManager: AppUpdateManager? = null
    private val REQUEST_CODE = 100

    override fun onDestroy() {
        bp?.release()
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        bottomNavigationView = findViewById(R.id.nav_view)
        bottomNavigation()
        MobileAds.initialize(this)
        donationsInit()
        loadInterAd()


        appUpdateManager = AppUpdateManagerFactory.create(this)
        checkUpdate()

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        deviceSensors = convertSensor(sensorManager.getSensorList(Sensor.TYPE_ALL))
    }

    private fun checkUpdate() {
        appUpdateManager?.appUpdateInfo?.addOnSuccessListener { updateInfo ->
            if (updateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && updateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                appUpdateManager?.startUpdateFlowForResult(
                    updateInfo,
                    AppUpdateType.IMMEDIATE,
                    this,
                    REQUEST_CODE
                )

            }
        }
    }

    override fun onResume() {
        super.onResume()
        inProgressUpdate()
    }

    private fun inProgressUpdate() {
        appUpdateManager?.appUpdateInfo?.addOnSuccessListener { updateInfo ->
            if (updateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                appUpdateManager?.startUpdateFlowForResult(
                    updateInfo,
                    AppUpdateType.IMMEDIATE,
                    this,
                    REQUEST_CODE
                )
            }
        }
    }


    private fun bottomNavigation() {
        //val navView = binding.navView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        //val navController = findNavController(R.id.fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_main, R.id.navigation_all_sensors, R.id.navigation_help
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        val menuItems = arrayOf(
            CbnMenuItem(
                R.drawable.ic_home,
                R.drawable.avd_home,
                R.id.navigation_main
            ),
            CbnMenuItem(
                R.drawable.ic_list,
                R.drawable.avd_list,
                R.id.navigation_all_sensors
            ),
            CbnMenuItem(
                R.drawable.ic_outline_info_24,
                R.drawable.avd_info,
                R.id.navigation_help
            )
        )
        binding.navView.setMenuItems(menuItems, 0)
        binding.navView.setupWithNavController(navController)
    }

    private fun loadInterAd() {
        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(
            this,
            AD_UNIT_ID,
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mRewardedAd = null
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    mRewardedAd = rewardedAd
                }
            }
        )
    }

    fun showInterAd() {
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
                    }
                }

            mRewardedAd?.show(
                this
            ) {
                showContent()
            }
        }
    }

    private fun showContent() {
        showToast(getString(R.string.thank_you_for_watching_ads))
    }

    private fun donationsInit() {
        bp = BillingProcessor.newBillingProcessor(this, BILLING_KEY, this)
        bp?.initialize()
    }

    override fun onProductPurchased(productId: String, details: PurchaseInfo?) {
        bp?.consumePurchaseAsync(productId, object : BillingProcessor.IPurchasesResponseListener {
            override fun onPurchasesSuccess() {
            }

            override fun onPurchasesError() {
            }
        })
    }

    override fun onPurchaseHistoryRestored() {
    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {
        showToast("Error ${error?.localizedMessage}")
    }

    override fun onBillingInitialized() {
    }
}