package com.emiratesauction.weather.city.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
@Parcelize
@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "weatherId")
    var id: Int? = null,
    @ColumnInfo(name = "weatherCityId")
    var cityId: Int? = null,
    var main: String? = null,
    var description: String? = null,
    var icon: String? = null
) : Parcelable
