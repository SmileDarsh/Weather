package com.emiratesauction.weather.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.emiratesauction.weather.city.domain.model.Weather

/**
 * Created by µðšţãƒâ ™ on 05/06/2021.
 * ->
 */
@Dao
interface WeatherDao {
    @Insert(onConflict = REPLACE)
    fun insertWeather(weather: Weather?): Long

    @Delete
    fun deleteWeather(weather: Weather?)
}