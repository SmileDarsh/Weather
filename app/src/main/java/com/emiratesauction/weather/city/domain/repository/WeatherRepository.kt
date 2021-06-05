package com.emiratesauction.weather.city.domain.repository

import com.emiratesauction.weather.city.domain.model.City
import kotlinx.coroutines.flow.Flow

/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
interface WeatherRepository {
    fun getCity(text : String): Flow<City>

    fun getWeathers(text : String): Flow<MutableList<City>>

    fun saveCity(city : City): Flow<Boolean>

    fun removeCity(city : City): Flow<Boolean>
}