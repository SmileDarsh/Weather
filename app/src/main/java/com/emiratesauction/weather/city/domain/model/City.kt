package com.emiratesauction.weather.city.domain.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
@Parcelize
@Entity(tableName = "city")
data class City(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "cityId")
    var id: Int? = null,
    var message: String? = null,
    var name: String? = null,
    var dt: Long? = null,
    var dt_txt: String? = null,
    @Embedded(prefix = "main_")
    var main: Main? = null,
    @Embedded(prefix = "wind_")
    var wind: Wind? = null,
    var weather: MutableList<Weather>? = null,
    var save: Int? = null
) : Parcelable
