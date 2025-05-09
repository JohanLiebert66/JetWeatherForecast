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