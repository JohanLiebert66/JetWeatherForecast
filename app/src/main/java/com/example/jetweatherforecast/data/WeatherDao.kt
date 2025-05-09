package com.example.jetweatherforecast.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetweatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow

import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.example.jetweatherforecast.model.Unit


@Dao
interface WeatherDao {

    @Query("SELECT * FROM fav_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM fav_tbl WHERE city = :city")
    fun getFavById(city: String): Flow<Favorite?>

    @Insert(onConflict = REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query("DELETE FROM fav_tbl")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    // Unit Table
    @Query("SELECT * FROM settings_tbl")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Update(onConflict = REPLACE)
    suspend fun updateUnit(unit: Unit)

    @Query("DELETE FROM settings_tbl")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnit(unit: Unit)

}


//@Dao
//interface WeatherDao {
//    @Query("SELECT * from fav_tbl")  // SQL query command
//    fun getFavorites(): Flow<List<@JvmSuppressWildcards Favorite>> // a stream of data that can be collected
//
//    @Query("SELECT * from fav_tbl WHERE city =:city") // get
//    suspend fun getFavById(city: String): Favorite?
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertFavorite(favorite: Favorite)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun updateFavorite(favorite: Favorite)
//
//    @Query("DELETE from fav_tbl")
//    suspend fun deleteAllFavorites()
//
//    @Delete
//    suspend fun deleteFavorite(favorite: Favorite)
//
//}

