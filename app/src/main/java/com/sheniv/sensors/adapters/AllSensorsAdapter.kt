package com.sheniv.sensors.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sheniv.sensors.R
import com.sheniv.sensors.databinding.ItemSensorMainBinding
import com.sheniv.sensors.models.SensorItem

class AllSensorsAdapter(
    private val listSensors: List<SensorItem>,
    private val choiceSensor: ChoiceSensor
) : RecyclerView.Adapter<AllSensorsAdapter.ASViewHolder>() {

    inner class ASViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemSensorMainBinding.bind(item)
        fun bind(sensorItem: SensorItem) = with(binding){
            imageButton.setImageResource(sensorItem.image)
            nameSensor.setText(sensorItem.sensor_name)
            imageButton.setOnClickListener{
                choiceSensor.choiceSensor(sensorItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ASViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_sensor_main, parent, false)
        )

    override fun onBindViewHolder(holder: AllSensorsAdapter.ASViewHolder, position: Int) {
        holder.bind(listSensors[position])
    }

    override fun getItemCount() = listSensors.size
}