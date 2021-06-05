package com.emiratesauction.weather.room.embeddedModel

import androidx.room.Embedded
import com.emiratesauction.weather.city.domain.model.City
import com.emiratesauction.weather.city.domain.model.Main
import com.emiratesauction.weather.city.domain.model.Weather
import com.emiratesauction.weather.city.domain.model.Wind

/**
 * Created by µðšţãƒâ ™ on 05/06/2021.
 *  ->
 */
data class CityEmbedded(
    @Embedded val city: City,
    @Embedded val weather: Weather,
    @Embedded val wind: Wind,
    @Embedded val main: Main
)
