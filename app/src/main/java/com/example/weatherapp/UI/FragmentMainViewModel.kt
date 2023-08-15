package com.example.weatherapp.UI

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Data.Forecast
import com.example.weatherapp.Data.Forecastday
import com.example.weatherapp.Data.WeatherData
import com.example.weatherapp.Model.ApiService
import com.example.weatherapp.Model.RetrofitInstance
import com.example.weatherapp.Model.RetrofitRepository
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import retrofit2.create


class FragmentMainViewModel : ViewModel() {

    private val _loadingWeatherData = MutableLiveData<WeatherData>()
    val loadingWeatherData: LiveData<WeatherData> = _loadingWeatherData

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val response = RetrofitInstance.retrofit().create(ApiService::class.java)
            val result = response.getForecast()

            _loadingWeatherData.postValue(result.body())
            Log.d("LOG", "Number of items: ${result.body()}")
        }
    }
}

