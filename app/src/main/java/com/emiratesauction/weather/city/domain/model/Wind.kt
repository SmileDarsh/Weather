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
@Entity(tableName = "wind")
data class Wind(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "windId")
    var id: Int? = null,
    @ColumnInfo(name = "windCityId")
    var cityId: Int? = null,
    var deg: Int? = null,
    var speed: Double? = null
) : Parcelable