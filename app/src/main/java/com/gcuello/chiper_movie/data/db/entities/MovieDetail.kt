package com.gcuello.chiper_movie.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_detail")
data class MovieDetail(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String?,
    @ColumnInfo(name = "belongs_to_collection")
    var belongsToCollection: Int?,//One to one reference table belongs_to_collection
    @ColumnInfo(name = "budget")
    var budget: Int?,
    @ColumnInfo(name = "genres")
    var genres: String?,//List of id's genres
    @ColumnInfo(name = "homepage")
    var homepage: String?,
    @ColumnInfo(name = "adult")
    var adult: Boolean?,
    @ColumnInfo(name = "imdb_id")
    var imdbId: String?,
    @ColumnInfo(name = "original_language")
    var originalLanguage: String?,
    @ColumnInfo(name = "original_title")
    var originalTitle: String?,
    @ColumnInfo(name = "overview")
    var overview: String?,
    @ColumnInfo(name = "popularity")
    var popularity: Double?,
    @ColumnInfo(name = "poster_path")
    var posterPath: String?,
    @ColumnInfo(name = "production_companies")
    var productionCompanies: String?,//Concat String List of id productionCompanies
    @ColumnInfo(name = "production_countries")
    var productionCountries: String?,//Concat String List of countries name
    @ColumnInfo(name = "release_date")
    var releaseDate: String?,
    @ColumnInfo(name = "revenue")
    var revenue: Int?,
    @ColumnInfo(name = "runtime")
    var runtime: Int?,
    @ColumnInfo(name = "spoken_languages")
    var spokenLanguages: String?,//Concat String List of spoken languages
    @ColumnInfo(name = "status")
    var status: String?,
    @ColumnInfo(name = "tagline")
    var tagline: String?,
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "video")
    var video: Boolean?,
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double?,
    @ColumnInfo(name = "vote_count")
    var voteCount: Int?
)