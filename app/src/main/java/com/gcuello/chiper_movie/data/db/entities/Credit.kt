package com.gcuello.chiper_movie.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credits")
data class Credit(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id:Int?,
    @ColumnInfo(name = "cast")
    var cast:String?,
    @ColumnInfo(name = "crew")
    var crew:String?
)
