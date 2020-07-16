package com.elfalt.weatherforecast.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofitClient : Retrofit? = null

    fun getClient() : Retrofit{

        if(retrofitClient == null){
            retrofitClient = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofitClient!!
    }
}