package com.elfalt.weatherforecast.models

data class WeatherResponse(val name: String, val weather: List<WeatherInfo>, val main : Weather)

data class Weather(val temp : Double)