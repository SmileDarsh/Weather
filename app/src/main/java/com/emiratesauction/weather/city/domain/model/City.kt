package com.emiratesauction.weather.city.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.emiratesauction.weather.room.embeddedModel.CityEmbedded
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
    @Ignore
    var main: Main? = null,
    @Ignore
    var wind: Wind? = null,
    @Ignore
    var weather: MutableList<Weather>? = null,
    var save: Int? = null
) : Parcelable {

    fun addCity(cityEmbedded: CityEmbedded) : City{
        this.id = cityEmbedded.city.id
        this.name = cityEmbedded.city.name
        this.dt = cityEmbedded.city.dt
        this.dt_txt = cityEmbedded.city.dt_txt
        this.save = cityEmbedded.city.save
        this.main = cityEmbedded.main
        this.wind = cityEmbedded.wind
        this.weather = mutableListOf(cityEmbedded.weather)
        return this
    }

}
