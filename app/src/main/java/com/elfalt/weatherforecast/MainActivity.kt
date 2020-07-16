package com.elfalt.weatherforecast

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.elfalt.weatherforecast.models.WeatherInfo
import com.elfalt.weatherforecast.models.WeatherResponse
import com.elfalt.weatherforecast.network.ApiService
import com.elfalt.weatherforecast.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var textCount : Int = 0

    private lateinit var apiService : ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                refreshWeather()
            }else{
                searchForWeather(enter_location.text.toString())
                enter_location.setText("")
            }
        }
    }

    private fun searchForWeather(city: String) {

        apiService.getWeatherCity(city,"758e01569bfe7fa793f65dd3594fd946")
            .enqueue(object : Callback<WeatherResponse>{
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"Error onFailure",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if(response.isSuccessful){
                        val res = response.body()!!
                        cityName.text = res.name
                        val weather : List<WeatherInfo> = res.weather
                        temp_description.text = weather[0].main
                        weatherIcon.setImageResource(getImageDrawable(res.weather[0].id))
                        celsius_temperature.text = res.main.temp.toString()
                    }
                }

            })

        Toast.makeText(this,"search for $city weather",Toast.LENGTH_SHORT).show()
    }

    private fun refreshWeather() {
        Toast.makeText(this,"refreshed",Toast.LENGTH_SHORT).show()
    }
    private fun getImageDrawable(id : Int) : Int{

        return when(id){
            200,201,202,210,211,212,221,230,231,232 -> R.drawable.ic_wi_thunderstorm
            300,301,302,310,311,312,313,314,321 -> R.drawable.ic_wi_sprinkle
            500, 501, 502,503,504,511,520,521,522,531,701 -> R.drawable.ic_wi_rain
            600,601, 602,611,612, 613,615,616,620,621,622 -> R.drawable.ic_wi_snow
            711 -> R.drawable.ic_wi_smoke
            721 -> R.drawable.ic_wi_day_haze
            731,761,762 -> R.drawable.ic_wi_dust
            741 -> R.drawable.ic_wi_fog
            771 -> R.drawable.ic_wi_cloudy_gusts
            781 -> R.drawable.ic_wi_tornado
            800 -> R.drawable.ic_wi_day_sunny
            801,802,803,804 -> R.drawable.ic_wi_cloudy
            else -> R.drawable.ic_wi_alien
        }
    }
}
