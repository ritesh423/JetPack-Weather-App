package com.riteshapps.jetpackweatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.riteshapps.jetpackweatherapp.composable.WeatherScreen
import com.riteshapps.jetpackweatherapp.utils.Routes

@Composable
fun Navigation(navController: NavHostController , modifier: Modifier) {
    NavHost(navController, startDestination= Routes.HOME, builder = {
        composable(Routes.HOME){
            WeatherScreen()
        }
    })
}