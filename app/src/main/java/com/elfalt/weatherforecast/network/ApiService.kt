package com.elfalt.weatherforecast.network

import com.elfalt.weatherforecast.models.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    fun getWeatherCity(@Query("q")cityName : String
                        ,@Query("appid") apiKey:String) : Call<WeatherResponse>
}