package com.sheniv.sensors.extentions

import android.app.Activity
import android.hardware.Sensor
import android.hardware.SensorManager
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.anjlab.android.iab.v3.BillingProcessor
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.material.bottomnavigation.BottomNavigationView
import np.com.susanthapa.curved_bottom_navigation.CurvedBottomNavigationView

lateinit var sensorManager: SensorManager
lateinit var bottomNavigationView: CurvedBottomNavigationView
const val AD_UNIT_ID = "ca-app-pub-2440252298457934/8027979890"
const val BILLING_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgB6sQu/cyeLXLEj1iPP/xtUtugV6MtZuWUfx1OE6eS9w4JQ4abTh5Ip5Vvggn9wuVCgeDcHj1koKvs+w38pCEzz61GpXZ+v8St+rIzPZm4zbYPVU1itFO/68R/w/R/ttEFOg+EaKQsBCpuPwG5WpZiF6nGCJF8+P9n+5wrsJKt1hlkD8a11HBZMPpRXmrvpsCyiQiBwgjWGtqj3jvavHuaMZuXpouTn0YsqdswgQplkneyqqiTqwW1p7xNPb/BG3fH8//FNv49Yu62orgvgY58UgaMWisqF4CKW8mOhcQ0JzBjwQQW5B5Dd+jDqumRzumkMUDHWA923DVniIxgOoeQIDAQAB"

var mRewardedAd: RewardedAd? = null
var bp: BillingProcessor? = null

lateinit var deviceSensors: List<Sensor>

fun View.beVisible() {
    this.visibility = View.VISIBLE
}

fun View.beGone() {
    this.visibility = View.GONE
}

fun View.beInvisible() {
    this.visibility = View.INVISIBLE
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}