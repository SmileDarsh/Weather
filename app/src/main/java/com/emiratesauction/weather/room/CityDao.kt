package com.emiratesauction.weather.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.emiratesauction.weather.city.domain.model.City

/**
 * Created by µðšţãƒâ ™ on 04/06/2021.
 * ->
 */
@Dao
interface CityDao {
    @Query("SELECT * FROM city")
    fun getCities(): MutableList<City>?

    @Query("SELECT COUNT(*) FROM city WHERE cityId = :cityId")
    fun isExist(cityId: Int): Int

    @Insert(onConflict = REPLACE)
    fun insertCity(city: City): Long

    @Delete
    fun deleteCity(city: City)
}