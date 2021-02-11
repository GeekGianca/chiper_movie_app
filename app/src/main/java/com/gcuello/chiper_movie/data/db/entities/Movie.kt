package com.gcuello.chiper_movie.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "release_date")
    var releaseDate: String?,
    @ColumnInfo(name = "type")
    var type: String?,
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String?,
    @ColumnInfo(name = "original_language")
    var originalLanguage: String?,
    @ColumnInfo(name = "genre_ids")
    var genreIds: String?,
    @ColumnInfo(name = "original_title")
    var originalTitle: String?,
    @ColumnInfo(name = "overview")
    var overview: String?,
    @ColumnInfo(name = "popularity")
    var popularity: Double?,
    @ColumnInfo(name = "poster_path")
    var posterPath: String?,
    @ColumnInfo(name = "video")
    var video: Boolean?,
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double?,
    @ColumnInfo(name = "vote_count")
    var voteCount: Int?,
    @ColumnInfo(name = "adult")
    var adult: Boolean?
)