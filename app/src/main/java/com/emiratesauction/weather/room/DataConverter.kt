package com.emiratesauction.weather.room

import androidx.room.TypeConverter
import com.emiratesauction.weather.city.domain.model.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by µðšţãƒâ ™ on 06/06/2021.
 *  ->
 */
class DataConverter {

    @TypeConverter
    fun fromWeatherList(weathers: MutableList<Weather>?): String? {
        weathers?.let {
            val gson = Gson()
            val type = object : TypeToken<MutableList<Weather>>() {}.type
            return gson.toJson(weathers, type)
        }
        return null
    }

    @TypeConverter
    fun toWeatherList(weathersString: String?): MutableList<Weather>? {
        weathersString?.let {
            val gson = Gson()
            val type = object : TypeToken<MutableList<Weather>>() {}.type
            return gson.fromJson(weathersString, type)
        }
        return null
    }
}