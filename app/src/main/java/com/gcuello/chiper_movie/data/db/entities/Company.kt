package com.gcuello.chiper_movie.data.db.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "production_companies")
data class Company(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "logo_path")
    var logoPath: String?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "origin_country")
    var originCountry: String?,
    @Ignore
    var logoPicture: Bitmap?
)