package com.sheniv.sensors.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sheniv.sensors.R
import com.sheniv.sensors.databinding.ItemSensorBinding
import com.sheniv.sensors.extentions.beGone
import com.sheniv.sensors.extentions.beVisible
import com.sheniv.sensors.extentions.deviceSensors
import com.sheniv.sensors.models.SensorInner
import java.util.*

class SensorAdapter(
    //private val listSensor: List<SensorInner>,
    private val searchInGoogle: SearchInGoogle
) : RecyclerView.Adapter<SensorAdapter.SensorViewHolder>() {

    inner class SensorViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemSensorBinding.bind(item)
        fun bind(sensor: SensorInner, pos: Int) = with(binding) {
            val rightName = sensor.name
            name.text = rightName.substring(0,1).uppercase() + rightName.substring(1).lowercase()


            search.setOnClickListener {
                searchInGoogle.search(sensor.name, sensor.vendor)
            }

            imageItem.setImageResource(
                when (sensor.type) {
                    1, 35 -> R.drawable.accelerometer_small
                    2, 14 -> R.drawable.geomagnetic_field_small
                    3 -> R.drawable.orientation_small
                    4, 16 -> R.drawable.gyroscope_small
                    5 -> R.drawable.light_small
                    8 -> R.drawable.proximity_small
                    9 -> R.drawable.gravity_small
                    10 -> R.drawable.linear_acceleration_small
                    11, 15, 20 -> R.drawable.rotation_vector_small
                    12 -> R.drawable.relative_humidity_small
                    13 -> R.drawable.temperature_small
                    18, 19 -> R.drawable.step_counter_small
                    65544 -> R.drawable.phone_call_small
                    65538 -> R.drawable.hall_small
                    else -> R.drawable.noname_small
                }
            )

            checkboxSetting.setOnCheckedChangeListener { qq, checked ->
                if (checked) {
                    qq.animate().rotation(180f).duration = 400L
                    vendor.text = sensor.vendor
                    version.text = sensor.version.toString()
                    type.text = sensor.type.toString()
                    maxRange.text = sensor.maximumRange.toString()
                    resolution.text = sensor.resolution.toString()
                    power.text = sensor.power.toString()
                    minDelay.text = sensor.minDelay.toString()
                    stringType.text = sensor.stringType
                    group.beVisible()
                } else {
                    qq.animate().rotation(0f).duration = 400L
                    group.beGone()
                    notifyItemChanged(pos)
                }
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
        holder.bind(deviceSensors[position], position)
    }

    override fun getItemCount() = deviceSensors.size
}