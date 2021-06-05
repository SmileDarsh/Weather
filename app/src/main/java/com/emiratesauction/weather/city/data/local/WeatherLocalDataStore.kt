package com.emiratesauction.weather.city.data.local

import com.emiratesauction.weather.city.domain.model.City
import com.emiratesauction.weather.room.RoomDB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by µðšţãƒâ ™ on 05/06/2021.
 *  ->
 */
class WeatherLocalDataStore(private val mRoomDB: RoomDB) {

    fun getCity(): Flow<City> = flow {
        val dbList = mRoomDB.cityDao()?.getCities()
        if (dbList != null && dbList.size > 0) {
            dbList.forEach { emit(City().addCity(it)) }
        } else {
            emit(City())
        }
    }

    fun saveCity(city: City): Flow<Boolean> = flow {
        if (mRoomDB.cityDao()?.isExist(city.id ?: 0) == 0) {
            city.main?.cityId = city.id
            city.wind?.cityId = city.id
            mRoomDB.cityDao()?.insertCity(city)
            mRoomDB.mainDao()?.insertMain(city.main)
            mRoomDB.windDao()?.insertWind(city.wind)
            city.weather?.let {
                it.first().cityId = city.id
                mRoomDB.weatherDao()?.insertWeather(it.first())
            }
            emit(true)
        } else {
            emit(false)
        }
    }

    fun removeCity(city: City): Flow<Boolean> {
        return flow {
            mRoomDB.cityDao()?.deleteCity(city)
            mRoomDB.mainDao()?.deleteMain(city.main)
            mRoomDB.windDao()?.deleteWind(city.wind)
            city.weather?.let {
                mRoomDB.weatherDao()?.deleteWeather(it.first())
            }
            emit(true)
        }
    }
}