package com.gcuello.chiper_movie.domain.usecases

import com.gcuello.chiper_movie.domain.model.Genres
import io.reactivex.Single

interface IDataSourceGenre {
    fun genreMovieList(params: Map<String, String>): Single<Genres>

    fun genreTvList(params: Map<String, String>): Single<Genres>
}