package com.gcuello.chiper_movie.data.datasource

import com.gcuello.chiper_movie.core.ServiceGenerator
import com.gcuello.chiper_movie.data.apiservice.MovieClient
import com.gcuello.chiper_movie.domain.model.Credits
import com.gcuello.chiper_movie.domain.model.DetailMovie
import com.gcuello.chiper_movie.domain.model.ExposeListData
import com.gcuello.chiper_movie.domain.model.Movie
import com.gcuello.chiper_movie.domain.usecases.IDetailDataSource
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
class DetailRemoteDataSource : IDetailDataSource {
    private val client: MovieClient = ServiceGenerator.createService(MovieClient::class.java)

    override fun detailsMovie(movieId: Int, params: Map<String, String>): Observable<DetailMovie> {
        return client.detailsMovie(movieId, params)
    }

    override fun credits(movieId: Int, params: Map<String, String>): Observable<Credits> {
        return client.credits(movieId, params)
    }

    override fun similarMovieList(
        movieId: Int,
        params: Map<String, String>
    ): Single<ExposeListData<List<Movie>>> {
        return client.similarMovieList(movieId, params)
    }

}