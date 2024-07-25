package com.example.threekingdom.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FuJiang (
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "img")val img:String,
    @ColumnInfo(name = "name")val name:String,
    @ColumnInfo(name = "growth")val growth:String,
    @ColumnInfo(name = "hp")val hp:String,
    @ColumnInfo(name = "dp")val dp:String,
    @ColumnInfo(name = "speed")val speed:String,
    @ColumnInfo(name = "power")val power:String
)