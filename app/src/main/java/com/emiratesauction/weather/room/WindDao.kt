package com.emiratesauction.weather.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.emiratesauction.weather.city.domain.model.Wind

/**
 * Created by µðšţãƒâ ™ on 05/06/2021.
 * ->
 */
@Dao
interface WindDao {
    @Insert(onConflict = REPLACE)
    fun insertWind(wind: Wind?): Long

    @Delete
    fun deleteWind(wind: Wind?)
}