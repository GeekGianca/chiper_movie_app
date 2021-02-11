package com.gcuello.chiper_movie.data.db.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "cast")
data class Cast(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "cast_id")
    var castId: Int?,
    @ColumnInfo(name = "character")
    var character: String?,
    @ColumnInfo(name = "credit_id")
    var creditId: String?,
    @ColumnInfo(name = "gender")
    var gender: Int?,
    @ColumnInfo(name = "adult")
    var adult: Boolean?,
    @ColumnInfo(name = "known_for_department")
    var knownForDepartment: String?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "order")
    var order: Int?,
    @ColumnInfo(name = "original_name")
    var originalName: String?,
    @ColumnInfo(name = "popularity")
    var popularity: Double?,
    @ColumnInfo(name = "profile_path")
    var profilePath: String?
)