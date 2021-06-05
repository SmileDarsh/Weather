package com.emiratesauction.weather.city.domain.interactor

import com.emiratesauction.weather.network.Interactor
import com.emiratesauction.weather.city.domain.model.City
import com.emiratesauction.weather.city.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by µðšţãƒâ ™ on 05/06/2021.
 *  ->
 */
class SaveCityInteractor(private val mWeatherRepository: WeatherRepository) :
    Interactor<City, Flow<Boolean>> {
    override fun execute(params: City): Flow<Boolean> = mWeatherRepository.saveCity(params)
}