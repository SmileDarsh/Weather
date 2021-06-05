package com.emiratesauction.weather.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.emiratesauction.weather.city.domain.model.City
import com.emiratesauction.weather.room.embeddedModel.CityEmbedded

/**
 * Created by µðšţãƒâ ™ on 04/06/2021.
 * ->
 */
@Dao
interface CityDao {
    @Query(
        """SELECT city.* , wind.* , main.* , weather.* FROM main
        INNER JOIN city ON mainCityId = cityId
        INNER JOIN wind ON cityId = windCityId
        INNER JOIN weather ON weatherCityId = cityId"""
    )
    fun getCities(): MutableList<CityEmbedded>?

    @Query("SELECT COUNT(*) FROM city WHERE cityId = :cityId")
    fun isExist(cityId: Int): Int

    @Insert(onConflict = REPLACE)
    fun insertCity(city: City): Long

    @Delete
    fun deleteCity(city: City)
}