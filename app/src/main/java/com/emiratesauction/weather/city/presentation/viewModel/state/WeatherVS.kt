package com.emiratesauction.weather.city.presentation.viewModel.state

import com.emiratesauction.weather.city.domain.model.City


/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
sealed class WeatherVS {
    class AddCity(val city: City) : WeatherVS()
    class AddWeathers(val weather: MutableList<City>) : WeatherVS()
    class SaveCity(val city: City) : WeatherVS()
    class RemoveCity(val city: City) : WeatherVS()
    class Error(val message: String) : WeatherVS()
    object Empty : WeatherVS()
    object InternetError : WeatherVS()
}
