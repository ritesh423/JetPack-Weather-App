package com.riteshapps.jetpackweatherapp.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.riteshapps.jetpackweatherapp.R
import com.riteshapps.jetpackweatherapp.utils.Constants
import com.riteshapps.jetpackweatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen() {
    val viewModel: WeatherViewModel = viewModel()
    val weatherData by viewModel.weatherData.collectAsState()

    var city by remember {
        mutableStateOf("")
    }
    val apiKey = Constants.API_KEY

    // Gradient background (sky-inspired)
    val backgroundGradient = Brush.verticalGradient(
        listOf(
            Color(0xFFF7F8FA), // very light gray (top)
            Color(0xFFECECEC), // slightly darker gray (bottom)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // App title
            Text(
                modifier = Modifier.padding(0.dp,10.dp,0.dp,10.dp),
                text = "Weather App",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )

            TextField(
                value = city,
                onValueChange = { city = it },
                placeholder = { Text("Type your city") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                singleLine = true
            )


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (city.isNotBlank()) {
                        viewModel.fetchWeather(city, apiKey)
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2A2525)),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
                    .padding(0.dp,0.dp,0.dp,10.dp)
            ) {
                Text("Check Weather", fontSize = 18.sp, color = Color.White)
            }

            // Weather cards grid
            weatherData?.let {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        WeatherCard(
                            icon = R.drawable.ic_globe,
                            label = "City",
                            value = it.name,
                            modifier = Modifier.weight(1f)
                        )
                        WeatherCard(
                            icon = R.drawable.ic_thermostat,
                            label = "Temperature",
                            value = "${it.main.temp}°C",
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        WeatherCard(
                            icon = R.drawable.ic_humidity,
                            label = "Humidity",
                            value = "${it.main.humidity}",
                            modifier = Modifier.weight(1f)
                        )
                        WeatherCard(
                            icon = R.drawable.ic_haze,
                            label = "Description",
                            value = it.weather[0].description,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun WeatherCard(
    icon: Int,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .height(180.dp),  // Adjusted for your row layout
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF171616)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF2A2A2D), Color(0xFF1A1A1C))
                    )
                )
                .padding(16.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(42.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = label,
                color = Color(0xFFB0B0B0),
                fontSize = 14.sp
            )
            Text(
                text = value,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(5.dp)

            )
        }
    }
}
