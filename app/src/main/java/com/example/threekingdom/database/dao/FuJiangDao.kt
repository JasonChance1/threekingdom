package com.example.threekingdom.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.threekingdom.database.entity.FuJiang

@Dao
interface FuJiangDao {
    @Insert
    fun insert(vararg fujiang: FuJiang)

    @Update
    fun update(vararg fujiang: FuJiang)

    @Delete
    fun delete(vararg fujiang: FuJiang)


    @Query("SELECT * FROM fujiang")
    fun getAll(): List<FuJiang>

}