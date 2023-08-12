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
import com.example.weatherapp.Data.WeatherData
import com.example.weatherapp.Model.RetrofitRepository
import com.example.weatherapp.databinding.FragmentMainBinding

class FragmentMain : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: AdapterWeatherAPI

    private val viewModel   : FragmentMainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AdapterWeatherAPI()

        Log.d("LOG", "Адаптер загружен")
        binding.Recycler.adapter = adapter

        loadData()

    }

    fun loadData(){
        viewModel.loadingWeatherData.observe(viewLifecycleOwner){it ->
            val list = listOf(it)
            adapter.submitList(list)
            Log.d("LOG", "${it.forecast}${it.current}${it.location}")
            binding.CityName.text = it.location.name
            binding.Celsius.text = it.current.temp_c.toString() + "°C"
        }
    }


}