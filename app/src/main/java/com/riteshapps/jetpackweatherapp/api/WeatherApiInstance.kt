package com.riteshapps.jetpackweatherapp.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.riteshapps.jetpackweatherapp.models.WeatherResponse
import com.riteshapps.jetpackweatherapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInstance {
    @GET("weather")
    suspend fun getCityWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = Constants.API_KEY,
        @Query("units") units: String = "metric"
    ): WeatherResponse

    companion object {
        fun create(context: Context): WeatherApiInstance {
            // ✅ Create OkHttpClient and attach Chucker
            val client = OkHttpClient.Builder()
                .addInterceptor(ChuckerInterceptor.Builder(context).build())
                .build()

            // ✅ Pass client to Retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(WeatherApiInstance::class.java)
        }
    }
}