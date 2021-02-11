package com.gcuello.chiper_movie.data.db.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "belongs_to_collection")
data class BelongsToCollection(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "poster_path")
    var posterPath: String?,
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String?,
    @Ignore
    var backdropPicture: Bitmap?,
    @Ignore
    var posterPicture: Bitmap?
)