package com.emiratesauction.weather.network

import com.google.gson.Gson
import retrofit2.HttpException
import java.lang.Exception

/**
 * Created by µðšţãƒâ ™ on 04/06/2020.
 *  ->
 */

fun Exception.notFound(): ErrorResponse? {
    if (this is HttpException) {
        if (response() != null) {
            val response = response()!!
            if (response.code() == 404) {
                return response.errorBody()?.let {
                    return Gson().fromJson(
                        it.charStream(), ErrorResponse::class.java
                    )
                }
            }
        }
    }
    return null
}