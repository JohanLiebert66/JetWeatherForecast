package com.example.jetweatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetweatherforecast.screens.main.MainScreen
import com.example.jetweatherforecast.screens.main.MainViewModel
import com.example.jetweatherforecast.screens.search.SearchScreen
import com.example.jetweatherforecast.screens.splash.WeatherSplashScreen


@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {
        // construct our Navigation in the form of composables
        composable(WeatherScreens.SplashScreen.name) {
        WeatherSplashScreen(navController = navController)
        }

        val route = WeatherScreens.MainScreen.name


        composable(route = "$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                    defaultValue = "Seattle"
                    nullable = true
                }
            )
        ) { navBack ->
            navBack.arguments?.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, mainViewModel, city = city)
            }
        }

        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
    }
}