package com.elfalt.weatherforecast

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var textCount : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    private fun searchForWeather(text: String) {
        Toast.makeText(this,"search for weather",Toast.LENGTH_SHORT).show()
    }

    private fun refreshWeather() {
        Toast.makeText(this,"refreshed",Toast.LENGTH_SHORT).show()
    }
}
