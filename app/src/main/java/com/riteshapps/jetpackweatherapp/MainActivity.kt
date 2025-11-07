package com.riteshapps.jetpackweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.riteshapps.jetpackweatherapp.composable.WeatherScreen
import com.riteshapps.jetpackweatherapp.ui.theme.JetPackWeatherAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var keepSplash = true
        lifecycleScope.launch {
            delay(1500)
            keepSplash = false
        }

        installSplashScreen().setKeepOnScreenCondition { keepSplash }
        setContent {
            WeatherScreen()
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}