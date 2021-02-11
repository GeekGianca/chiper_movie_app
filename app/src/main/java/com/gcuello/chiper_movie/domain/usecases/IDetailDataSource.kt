package com.gcuello.chiper_movie.domain.usecases

import com.gcuello.chiper_movie.domain.model.Credits
import com.gcuello.chiper_movie.domain.model.DetailMovie
import com.gcuello.chiper_movie.domain.model.ExposeListData
import com.gcuello.chiper_movie.domain.model.Movie
import io.reactivex.Observable
import io.reactivex.Single

interface IDetailDataSource {

    fun detailsMovie(movieId: Int, params: Map<String, String>): Observable<DetailMovie>

    fun credits(movieId: Int, params: Map<String, String>): Observable<Credits>

    fun similarMovieList(
        movieId: Int,
        params: Map<String, String>
    ): Single<ExposeListData<List<Movie>>>
}