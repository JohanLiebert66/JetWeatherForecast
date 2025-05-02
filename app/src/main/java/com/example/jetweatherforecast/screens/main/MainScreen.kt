package com.example.jetweatherforecast.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.Weather
import com.example.jetweatherforecast.model.WeatherItem
import com.example.jetweatherforecast.navigation.WeatherScreens
import com.example.jetweatherforecast.utils.formatDate
import com.example.jetweatherforecast.utils.formatDecimals
import com.example.jetweatherforecast.widgets.HumidityWindPressureRow
import com.example.jetweatherforecast.widgets.SunsetSunRiseRow
import com.example.jetweatherforecast.widgets.WeatherAppBar
import com.example.jetweatherforecast.widgets.WeatherDetailRow
import com.example.jetweatherforecast.widgets.WeatherStateImage

@Composable
fun MainScreen (
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
) { // instanciate the view model
    //MainScaffold(mainViewModel)

    Log.d("TAG", "MainScreen: $city")

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city = city.toString())
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    }else if (weatherData.data != null) {
        //Text(text = "Main Screen ${weatherData.data!!.city.country}")
        MainScaffold(weather = weatherData.data!!, navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold (
        topBar = {
            WeatherAppBar(
                title = weather.city.name + ", ${weather.city.country}",
                //icon = Icons.AutoMirrored.Filled.ArrowBack,
                navController = navController,
                onAddActionClicked = {
                    // I want to navigate to Main Screen
                    navController.navigate(WeatherScreens.SearchScreen.name)

                },
                elevation = 5.dp
            ) {
                Log.d("TAG", "MainScaffold: Button Clicked")
            }
        }
    ){
        MainContent(data = weather, isImperial = false)
    }
}

@Composable
fun MainContent(data: Weather, isImperial: Boolean) {
    val weatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"

    Column(
        Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = formatDate(weatherItem.dt), // Wed Nov 30
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp))

        Surface(modifier = Modifier
            .padding(4.dp)
            .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                WeatherStateImage(imageUrl = imageUrl)
                Text(text = formatDecimals(weatherItem.temp.day) + "º",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold)
                Text(text = weatherItem.weather[0].main,
                    fontStyle = FontStyle.Italic)
            }
        }
        HumidityWindPressureRow(weather = data.list[0])
        HorizontalDivider()
        SunsetSunRiseRow(weather = data.list[0])
        // Title
        Text("This Week",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold)

        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ){
                items(items =  data.list) { item: WeatherItem ->
                    WeatherDetailRow(weather = item)

                }

            }

        }

    }

}

