package com.sheniv.sensors.base

import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sheniv.sensors.MainActivity
import com.sheniv.sensors.R
import com.sheniv.sensors.extentions.bp
import com.sheniv.sensors.extentions.sensorManager

abstract class OneParameterBaseFragment<VIEW_BINDING : ViewBinding> : BaseFragment<VIEW_BINDING>(),
    SensorEventListener {

    var sensor: Sensor? = null
    abstract val currentSensor: Int

    override fun VIEW_BINDING.onBindView(savedInstanceState: Bundle?) {
        checkingSensor(currentSensor)
    }

    fun checkingSensor(a: Int){
        if (sensorManager.getDefaultSensor(a) != null) {
            sensor = sensorManager.getDefaultSensor(a)
        } else {
            navController.popBackStack()
            showDialog()
            //navController.navigate(R.id.unfortunatelyFragment)
        }
    }

    private fun showDialog() {
        val dialogBinding = layoutInflater.inflate(R.layout.dialog_unfortunately, null)

        val width = (ViewGroup.LayoutParams.MATCH_PARENT).toInt()
        val height = ViewGroup.LayoutParams.WRAP_CONTENT

        val alertDialog = MaterialAlertDialogBuilder(requireActivity())
            .setView(dialogBinding)
            .create()
        alertDialog.show()
        alertDialog.window?.setLayout(width, height)

        val btn_exit = dialogBinding.findViewById<ImageView>(R.id.btn_close_dialog)
        btn_exit.setOnClickListener { alertDialog.dismiss() }
    }

    override fun onResume() {
        super.onResume()
        sensor?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}