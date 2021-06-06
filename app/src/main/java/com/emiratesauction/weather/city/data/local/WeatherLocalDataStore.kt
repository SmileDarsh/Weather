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
        if (dbList != null && dbList.isNotEmpty()) {
            dbList.forEach { emit(it) }
        } else {
            emit(City())
        }
    }

    fun saveCity(city: City): Flow<Boolean> = flow {
        if (mRoomDB.cityDao()?.isExist(city.id ?: 0) == 0) {
            mRoomDB.cityDao()?.insertCity(city)
            emit(true)
        } else {
            emit(false)
        }
    }

    fun removeCity(city: City): Flow<Boolean> {
        return flow {
            mRoomDB.cityDao()?.deleteCity(city)
            val dbList = mRoomDB.cityDao()?.getCities()
            if (dbList != null && dbList.isNotEmpty()) emit(true)
            else emit(false)
        }
    }
}