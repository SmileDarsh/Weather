package com.emiratesauction.weather.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.emiratesauction.weather.city.domain.model.City

/**
 * Created by µðšţãƒâ ™ on 04/06/2021.
 * ->
 */

@Database(entities = [City::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun cityDao(): CityDao?
}

fun provideDatabase(application: Application): RoomDB {
    return Room.databaseBuilder(application, RoomDB::class.java, "weather")
        .fallbackToDestructiveMigration()
        .build()
}