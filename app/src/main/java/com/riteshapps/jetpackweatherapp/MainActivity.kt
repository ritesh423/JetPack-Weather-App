package com.riteshapps.jetpackweatherapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.riteshapps.jetpackweatherapp.composable.MainScreen
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
            JetPackWeatherAppTheme {
                MainScreen()
            }
        }
    }
}

