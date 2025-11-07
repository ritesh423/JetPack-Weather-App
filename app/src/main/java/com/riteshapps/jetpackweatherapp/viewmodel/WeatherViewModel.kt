package com.riteshapps.jetpackweatherapp.viewmodel

import android.R.attr.apiKey
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riteshapps.jetpackweatherapp.api.WeatherApiInstance
import com.riteshapps.jetpackweatherapp.models.WeatherResponse
import com.riteshapps.jetpackweatherapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData
    private val weatherApi = WeatherApiInstance.create(context = application.applicationContext)



    fun fetchWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("Ritesh::", "Fetching weather for city=$city")
                val response = weatherApi.getCityWeather(city, Constants.API_KEY)
                Log.d("Ritesh::", "Response received: ${response.name}")
                _weatherData.value = response
            } catch (e: Exception) {
                Log.e("Ritesh::", "Error fetching weather: ${e.message}", e)
            }
        }
    }
}

