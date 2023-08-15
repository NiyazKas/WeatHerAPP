package com.example.weatherapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.weatherapp.Adapters.AdapterWeatherAPI
import com.example.weatherapp.Adapters.ExampleAdapter
import com.example.weatherapp.Data.ExampleClass
import com.example.weatherapp.Data.WeatherData
import com.example.weatherapp.Model.RetrofitRepository
import com.example.weatherapp.databinding.FragmentMainBinding

class FragmentMain : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: ExampleAdapter

    private val viewModel: FragmentMainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExampleAdapter()

        Log.d("LOG", "Адаптер загружен")
        binding.Recycler.adapter = adapter

        loadData()

    }

    fun loadData() {
            viewModel.loadingWeatherData.observe(viewLifecycleOwner) { weatherData ->
                val itemList: MutableList<ExampleClass> = mutableListOf()

                binding.CityName.text = weatherData.location.name
                binding.Date.text = weatherData.location.localtime
                binding.Celsius.text = weatherData.current.temp_c.toString()

                weatherData.forecast.forecastday.forEach { forecastDay ->
                    Log.d("Log", "icon items: ${weatherData.current.condition.icon}")
                    val item = ExampleClass(
                        condIcon = weatherData.current.condition.icon,
                        avghumidity = forecastDay.day.avghumidity,
                        maxwind_kph = forecastDay.day.maxwind_kph,
                        condText = weatherData.current.condition.text,
                        avgtemp_c = forecastDay.day.avgtemp_c
                    )
                    itemList.add(item)
                }
//            val item: List<ExampleClass> = listOf(
//                ExampleClass(
//                    condIcon = it.current.condition.icon,
//                    avghumidity = it.forecast.forecastday[3].day.avghumidity,
//                    maxwind_kph = it.forecast.forecastday[3].day.maxwind_kph,
//                    condText = it.current.condition.text,
//                    avgtemp_c = it.forecast.forecastday[3].day.avgtemp_c
//            ), ExampleClass(
//                condIcon = it.current.condition.icon,
//                avghumidity = it.forecast.forecastday[3].day.avghumidity,
//                maxwind_kph = it.forecast.forecastday[3].day.maxwind_kph,
//                condText = it.current.condition.text,
//                avgtemp_c = it.forecast.forecastday[3].day.avgtemp_c
//            )
//            )
                Log.d("Log", "is : ${itemList.size}")
                adapter.updateData(itemList)
//            Log.d("LOG", "Data received. Size: ${it.size}")
            }
        }

    }