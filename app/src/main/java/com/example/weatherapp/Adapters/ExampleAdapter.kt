package com.example.weatherapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.Data.WeatherData
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemLayoutBinding

class ExampleAdapter() : RecyclerView.Adapter<ExampleAdapter.ItemHolder>() {

    private val weatherDataList = mutableListOf<WeatherData>()

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemLayoutBinding.bind(view)

        fun bind(data: WeatherData) = with(binding) {
            val absoluteUrl = "http:" + data.current.condition.icon
            Glide.with(itemView.context)
                .asBitmap()
                .centerCrop()
                .load(absoluteUrl)
                .into(IW)

            textview1.text = data.current.condition.text
            Celcius.text =
                data.forecast.forecastday[adapterPosition].day.avgtemp_c.toString() + "°C"
            textview2.text = "max wind: " + data.forecast.forecastday[adapterPosition].day.maxwind_kph.toString()
            textview3.text =
                "humidity: " + data.forecast.forecastday[adapterPosition].day.avghumidity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(weatherDataList[position])
    }

    override fun getItemCount(): Int {
        return weatherDataList.size
    }

    // Функция для обновления данных
    fun updateData(newWeatherDataList: List<WeatherData>) {
        weatherDataList.clear()
        weatherDataList.addAll(newWeatherDataList)
        notifyDataSetChanged()
    }
}