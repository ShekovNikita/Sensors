package com.sheniv.sensors.adapters

import android.hardware.Sensor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sheniv.sensors.R
import com.sheniv.sensors.databinding.ItemSensorBinding
import com.sheniv.sensors.extentions.beGone
import com.sheniv.sensors.extentions.beVisible

class SensorAdapter(
    private val listSensor: List<Sensor>,
    private val searchInGoogle: SearchInGoogle
): RecyclerView.Adapter<SensorAdapter.SensorViewHolder>() {

    inner class SensorViewHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemSensorBinding.bind(item)
        fun bind(sensor: Sensor) = with(binding){
            name.text = sensor.name
            vendor.text = sensor.vendor
            version.text = sensor.version.toString()
            type.text = sensor.type.toString()
            maxRange.text = sensor.maximumRange.toString()
            resolution.text = sensor.resolution.toString()
            power.text = sensor.power.toString()
            minDelay.text = sensor.minDelay.toString()
            stringType.text = sensor.stringType

            search.setOnClickListener{
                searchInGoogle.search(sensor.name, sensor.vendor)
            }

            checkboxSetting.setOnCheckedChangeListener { _, checked ->
                if (checked) group.beVisible()
                else group.beGone()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SensorViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_sensor, parent, false)
        )

    override fun onBindViewHolder(holder: SensorAdapter.SensorViewHolder, position: Int) {
        holder.bind(listSensor[position])
    }

    override fun getItemCount() = listSensor.size
}