package com.emiratesauction.weather.network

import com.emiratesauction.weather.city.domain.model.City
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
interface IApiClient {
    @GET("weather")
    suspend fun getCity(@Query("q") text: String): City
}