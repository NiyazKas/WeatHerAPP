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


//class FragmentMainViewModel : ViewModel() {
//
//    private val repository = RetrofitRepository()
//    private val _loadingWeatherData = MutableLiveData<List<WeatherData>>()
//    val loadingWeatherData: LiveData<List<WeatherData>> = _loadingWeatherData
//
//    init {
//        loadData()
//    }
//
////    private fun loadData() {
////        viewModelScope.launch {
////            val response = RetrofitInstance.retrofit().create(ApiService::class.java)
////            val result = response.getForecast()
////            val dataList = listOf(result.body())
////
////
////
////            _loadingWeatherData.postValue(dataList as List<WeatherData>?)
////            Log.d("LOG", "Number of items: $result")
////        }
////    }
//
//    private fun loadData() {
//        viewModelScope.launch {
//            val response = RetrofitInstance.retrofit().create(ApiService::class.java)
//            val result = response.getForecast()
//
//            // Вместо оборачивания в список dataList, оборачиваем result.body() в список
//            _loadingWeatherData.postValue(listOf(result.body()) as List<WeatherData>?)
//        }
//    }
//}

//class FragmentMainViewModel : ViewModel() {
//
//    private val repository = RetrofitRepository()
//    private val _loadingWeatherData = MutableLiveData<List<Forecastday>>()
//    val loadingWeatherData: LiveData<List<Forecastday>> = _loadingWeatherData
//
//    init {
//        loadData()
//    }
//
//    private fun loadData() {
//        viewModelScope.launch {
//            val response = RetrofitInstance.retrofit().create(ApiService::class.java)
//            val result = response.getForecast()
//
//            // Extract the forecastday list from the result
//            val forecastDays = result.body()?.forecast?.forecastday ?: emptyList()
//
//            _loadingWeatherData.postValue(forecastDays)
//            Log.d("LOG", "Number of items: ${forecastDays.size}")
//        }
//    }
//}

class FragmentMainViewModel : ViewModel() {

    private val repository = RetrofitRepository()
    private val _loadingWeatherData = MutableLiveData<WeatherData>()
    val loadingWeatherData: LiveData<WeatherData> = _loadingWeatherData

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val response = RetrofitInstance.retrofit().create(ApiService::class.java)
            val result = response.getForecast()
//            val dataList = result.body()?.let {
//                listOf(it.location, it.current, it.forecast)
//            }

            _loadingWeatherData.postValue(result.body())
            Log.d("LOG", "Number of items: ${result.body()}")
        }
    }
}

