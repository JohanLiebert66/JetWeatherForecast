package com.example.jetweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetweatherforecast.model.Favorite


// WeatherDatabase --> Main Access point to Local data base for the app
@Database(entities = [Favorite::class, Unit::class], version = 2, exportSchema = false)

abstract class WeatherDatabase: RoomDatabase() { // You're not annotating a function, property, or parameter — you're annotating an entire class.

    abstract fun weatherDao(): WeatherDao

}