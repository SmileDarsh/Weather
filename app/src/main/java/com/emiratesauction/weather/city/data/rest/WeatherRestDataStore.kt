package com.emiratesauction.weather.city.data.rest

import com.emiratesauction.weather.network.IApiClient
import com.emiratesauction.weather.city.domain.model.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
class WeatherRestDataStore(private val iApiClient: IApiClient) {

    fun getCity(text: String): Flow<City> = flow {
        emit(iApiClient.getCity(text))
    }

    fun getWeathers(text: String): Flow<MutableList<City>> = flow {
        emit(iApiClient.getWeathers(text).list.filter { it.dt_txt?.contains("12:00:00")!! }.toMutableList())
    }
}