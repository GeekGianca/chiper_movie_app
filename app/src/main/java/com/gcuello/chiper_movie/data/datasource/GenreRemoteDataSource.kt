package com.gcuello.chiper_movie.data.datasource

import com.gcuello.chiper_movie.core.ServiceGenerator
import com.gcuello.chiper_movie.data.apiservice.MovieClient
import com.gcuello.chiper_movie.domain.model.Genre
import com.gcuello.chiper_movie.domain.model.Genres
import com.gcuello.chiper_movie.domain.usecases.IDataSourceGenre
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
class GenreRemoteDataSource : IDataSourceGenre {
    private val client = ServiceGenerator.createService(MovieClient::class.java)

    override fun genreMovieList(params: Map<String, String>): Single<Genres> =
        client.genreMovieList(params)

    override fun genreTvList(params: Map<String, String>): Single<Genres> =
        client.genreTvList(params)

}