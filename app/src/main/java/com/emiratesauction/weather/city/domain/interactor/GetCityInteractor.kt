package com.emiratesauction.weather.city.domain.interactor

import com.emiratesauction.weather.network.Interactor
import com.emiratesauction.weather.city.domain.model.City
import com.emiratesauction.weather.city.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
class GetCityInteractor(private val mWeatherRepository: WeatherRepository) :
    Interactor<String, Flow<City>> {
    override fun execute(params: String): Flow<City> = mWeatherRepository.getCity(params)
}