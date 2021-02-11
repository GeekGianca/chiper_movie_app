package com.gcuello.chiper_movie.data.apiservice

import com.gcuello.chiper_movie.domain.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MovieClient {
    @GET("/3/genre/movie/list")
    fun genreMovieList(@QueryMap params: Map<String, String>): Single<Genres>

    @GET("/3/genre/tv/list")
    fun genreTvList(@QueryMap params: Map<String, String>): Single<Genres>

    @GET("/3/movie/popular")
    fun popularMovieList(@QueryMap params: Map<String, String>): Single<ExposeListData<List<Movie>>>

    @GET("/3/movie/now_playing")
    fun nowPlayingList(@QueryMap params: Map<String, String>): Single<ExposeListData<List<Movie>>>

    @GET("/3/movie/top_rated")
    fun rateMovieList(@QueryMap params: Map<String, String>): Single<ExposeListData<List<Movie>>>

    @GET("/3/movie/upcoming")
    fun upcomingMovieList(@QueryMap params: Map<String, String>): Single<ExposeListData<List<Movie>>>

    @GET("/3/movie/{movie_id}")
    fun detailsMovie(
        @Path("movie_id") movieId: Int,
        @QueryMap params: Map<String, String>
    ): Observable<DetailMovie>

    @GET("/3/movie/{movie_id}/credits")
    fun credits(
        @Path("movie_id") movieId: Int,
        @QueryMap params: Map<String, String>
    ): Observable<Credits>

    @GET("/3/movie/{movie_id}/similar")
    fun similarMovieList(
        @Path("movie_id") movieId: Int,
        @QueryMap params: Map<String, String>
    ): Single<ExposeListData<List<Movie>>>
}