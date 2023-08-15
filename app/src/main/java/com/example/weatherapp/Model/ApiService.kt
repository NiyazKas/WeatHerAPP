package com.example.weatherapp.Model

import com.example.weatherapp.Const
import com.example.weatherapp.Data.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {


    @GET("forecast.json?key=${Const.API_KEY}&q=London&days=5&aqi=no&alerts=no")
    suspend fun getForecast(): Response<WeatherData>

}