package com.example.weatherapp.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.Data.Current
import com.example.weatherapp.Data.ExampleClass
import com.example.weatherapp.Data.Forecast
import com.example.weatherapp.Data.Forecastday
import com.example.weatherapp.Data.Location
import com.example.weatherapp.Data.WeatherData
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemLayoutBinding

class ExampleAdapter() : RecyclerView.Adapter<ExampleAdapter.ItemHolder>() {

    private var data: List<ExampleClass> = emptyList()

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemLayoutBinding.bind(view)

        fun bind(data: ExampleClass) = with(binding) {
            val absoluteUrl = "http:" + data.condIcon
            Glide.with(itemView.context)
                .asBitmap()
                .centerCrop()
                .load(absoluteUrl)
                .into(IW)
            textview1.text = data.condText
            textview2.text = data.maxwind_kph.toString()
            textview3.text = data.avghumidity.toString()
            Celcius.text = data.avgtemp_c.toString()

        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            return ItemHolder(view)
        }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data[position])
    }
        override fun getItemCount(): Int {
           return data.size
        }

        fun updateData(newWeatherData: List<ExampleClass>) {
            data = newWeatherData
            notifyDataSetChanged()
        }
    }

