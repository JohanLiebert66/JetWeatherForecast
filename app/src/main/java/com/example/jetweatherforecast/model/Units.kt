package com.example.jetweatherforecast.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "settings_tbl")
data class Units(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "units")
    val unit: String
)
