package com.example.jetweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetweatherforecast.model.Favorite
import com.example.jetweatherforecast.model.Units


// WeatherDatabase --> Main Access point to Local data base for the app
@Database(entities = [Favorite::class, Units::class], version = 2, exportSchema = false)

abstract class WeatherDatabase: RoomDatabase() { // You're not annotating a function, property, or parameter — you're annotating an entire class.

    abstract fun weatherDao(): WeatherDao

}