package com.emiratesauction.weather.city.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
@Parcelize
data class Main(
    var temp: Double? = null,
    var feels_like: Double? = null,
    var temp_min: Double? = null,
    var temp_max: Double? = null,
    var pressure: Double? = null,
    var humidity: Double? = null,
) : Parcelable
