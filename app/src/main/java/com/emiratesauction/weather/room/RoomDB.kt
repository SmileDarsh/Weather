package com.emiratesauction.weather.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emiratesauction.weather.city.domain.model.Main
import com.emiratesauction.weather.city.domain.model.City
import com.emiratesauction.weather.city.domain.model.Weather
import com.emiratesauction.weather.city.domain.model.Wind

/**
 * Created by µðšţãƒâ ™ on 04/06/2021.
 * ->
 */


@Database(
    entities = [City::class, Main::class, Weather::class, Wind::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {
    abstract fun cityDao(): CityDao?
    abstract fun weatherDao(): WeatherDao?
    abstract fun windDao(): WindDao?
    abstract fun mainDao(): MainDao?
}

fun provideDatabase(application: Application): RoomDB {
    return Room.databaseBuilder(application, RoomDB::class.java, "weather")
        .fallbackToDestructiveMigration()
        .build()
}