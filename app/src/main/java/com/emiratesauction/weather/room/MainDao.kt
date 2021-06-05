package com.emiratesauction.weather.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.emiratesauction.weather.city.domain.model.Main

/**
 * Created by µðšţãƒâ ™ on 05/06/2021.
 * ->
 */
@Dao
interface MainDao {
    @Insert(onConflict = REPLACE)
    fun insertMain(main: Main?): Long

    @Delete
    fun deleteMain(main: Main?)
}