package com.example.jetweatherforecast.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

// Custom shapes
val WeatherCardShape = RoundedCornerShape(20.dp)
val WeatherButtonShape = RoundedCornerShape(12.dp)
val WeatherDialogShape = RoundedCornerShape(16.dp)