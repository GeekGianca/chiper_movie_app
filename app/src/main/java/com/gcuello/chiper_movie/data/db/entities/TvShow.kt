package com.gcuello.chiper_movie.data.db.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show")
data class TvShow(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "overview")
    var overview: String?,
    @ColumnInfo(name = "genre_ids")
    var genreIds: String?,
    @ColumnInfo(name = "popularity")
    var popularity: Double?,
    @ColumnInfo(name = "vote_count")
    var voteCount: Int?,
    @ColumnInfo(name = "poster_path")
    var posterPath: String?,
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double?,
    @ColumnInfo(name = "original_name")
    var originalName: String?,
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String?,
    @ColumnInfo(name = "origin_country")
    var originCountry: String?,
    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String?,
    @ColumnInfo(name = "original_language")
    var originalLanguage: String?,
    @Ignore
    var picture: Bitmap?
)