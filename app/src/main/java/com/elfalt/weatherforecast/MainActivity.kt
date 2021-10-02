package com.elfalt.weatherforecast

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.elfalt.weatherforecast.models.WeatherInfo
import com.elfalt.weatherforecast.models.WeatherResponse
import com.elfalt.weatherforecast.network.ApiService
import com.elfalt.weatherforecast.network.RetrofitClient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var textCount : Int = 0
    private val apiKey = BuildConfig.ApiKey

    private lateinit var apiService : ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorPrimaryDark, theme)))
        }else{
            supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorPrimaryDark)))
        }

        apiService = RetrofitClient.getClient().create(ApiService::class.java)

        enter_location.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textCount = s.toString().trim().length
                search_fab.setImageResource( if(textCount==0) R.drawable.ic_refresh else R.drawable.ic_search)
            }
        })
        search_fab.setOnClickListener {
            if(textCount==0){
                refreshWeather(cityName.text.toString())
            }else{
                searchForWeather(enter_location.text.toString())
                enter_location.setText("")
            }
        }
    }

    private fun searchForWeather(city: String) {

        apiService.getWeatherCity(city,apiKey)
            .enqueue(object : Callback<WeatherResponse>{
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"Error onFailure",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if(response.isSuccessful){
                        val res = response.body()!!
                        cityName.text = res.name
                        val weather: List<WeatherInfo> = res.weather
                        temp_description.text = weather[0].main
                        Picasso.get()
                            .load("https://openweathermap.org/img/wn/" + res.weather[0].icon + "@4x.png")
                            .into(weatherIcon)
                        celsius_temperature.text = convertToCelsius(res.main.temp)

                    }else{
                        Toast.makeText(this@MainActivity,"wrong city name",Toast.LENGTH_SHORT).show()
                    }
                }

            })

    }

    private fun refreshWeather(city : String) {
        apiService.getWeatherCity(city,apiKey)
            .enqueue(object : Callback<WeatherResponse>{
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"Error onFailure",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if(response.isSuccessful){
                        val res = response.body()!!
                        val weather : List<WeatherInfo> = res.weather
                        temp_description.text = weather[0].main
                        Picasso.get().load("https://openweathermap.org/img/wn/${res.weather[0].icon}@4x.png").into(weatherIcon)
                        celsius_temperature.text = convertToCelsius(res.main.temp)
                        Toast.makeText(this@MainActivity,"refreshed",Toast.LENGTH_SHORT).show()
                    }
                }

            })
    }

    private fun convertToCelsius(temp : Float):String{
        return "%.2f".format(temp - 273.15)
    }
}
