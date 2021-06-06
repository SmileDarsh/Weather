package com.emiratesauction.weather.city.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
@Parcelize
data class Wind(
    var deg: Int? = null,
    var speed: Double? = null
) : Parcelable