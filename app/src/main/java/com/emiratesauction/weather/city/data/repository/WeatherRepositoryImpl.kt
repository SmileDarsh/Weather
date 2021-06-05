package com.emiratesauction.weather.city.data.repository

import android.content.Context
import com.emiratesauction.weather.city.data.local.WeatherLocalDataStore
import com.emiratesauction.weather.city.data.rest.WeatherRestDataStore
import com.emiratesauction.weather.city.domain.model.City
import com.emiratesauction.weather.city.domain.repository.WeatherRepository
import com.emiratesauction.weather.network.NO_INTERNET_FOUND
import com.emiratesauction.weather.network.NetworkManager
import kotlinx.coroutines.flow.Flow

/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
class WeatherRepositoryImpl(
    private val mWeatherRestDataStore: WeatherRestDataStore,
    private val mWeatherLocalDataStore: WeatherLocalDataStore,
    private val mContext: Context,
) : WeatherRepository {

    override fun getCity(text: String): Flow<City> {
        return if (NetworkManager.isOnline(mContext) && text.isNotEmpty()) {
            mWeatherRestDataStore.getCity(text)
        } else {
            mWeatherLocalDataStore.getCity()
        }
    }

    override fun getWeathers(text: String): Flow<MutableList<City>> {
        return if (NetworkManager.isOnline(mContext)) {
            mWeatherRestDataStore.getWeathers(text)
        } else {
            throw Exception(NO_INTERNET_FOUND)
        }
    }

    override fun saveCity(city: City): Flow<Boolean> = mWeatherLocalDataStore.saveCity(city)

    override fun removeCity(city: City): Flow<Boolean> = mWeatherLocalDataStore.removeCity(city)
}