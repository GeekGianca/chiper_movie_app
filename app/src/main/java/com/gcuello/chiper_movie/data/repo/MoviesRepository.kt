package com.gcuello.chiper_movie.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gcuello.chiper_movie.core.LoadingState
import com.gcuello.chiper_movie.data.datasource.MovieBoundaryCallback
import com.gcuello.chiper_movie.data.datasource.RateMovieBoundaryCallback
import com.gcuello.chiper_movie.data.db.datasource.MovieLocalDataSource
import com.gcuello.chiper_movie.data.db.entities.Movie
import com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters.AdapterPopularMovieItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val pMovieLocalDs: MovieLocalDataSource
) {
    private var movieBoundaryCallback: MovieBoundaryCallback? = null
    private var movieTopRateBoundaryCallback: RateMovieBoundaryCallback? = null

    fun fetchPagedListPopularMovie(): LiveData<PagedList<AdapterPopularMovieItem>> {
        movieBoundaryCallback = MovieBoundaryCallback(pMovieLocalDs, 1)
        val pagedListConfig = PagedList.Config.Builder()
            .setPrefetchDistance(2)
            .setEnablePlaceholders(true)
            .setPageSize(60)
            .build()
        val keyedPaged = pMovieLocalDs.pagedListPopularMovies("popular").map(::converter)
        return LivePagedListBuilder(
            keyedPaged,
            pagedListConfig
        ).setBoundaryCallback(movieBoundaryCallback).build()
    }

    fun fetchPagedListTopRateMovie(): LiveData<PagedList<AdapterPopularMovieItem>> {
        movieTopRateBoundaryCallback = RateMovieBoundaryCallback(pMovieLocalDs, 1)
        val pagedListConfig = PagedList.Config.Builder()
            .setPrefetchDistance(2)
            .setEnablePlaceholders(true)
            .setPageSize(60)
            .build()
        val keyedPaged = pMovieLocalDs.pagedListTopRateMovies("top-rated").map(::converter)
        return LivePagedListBuilder(
            keyedPaged,
            pagedListConfig
        ).setBoundaryCallback(movieTopRateBoundaryCallback).build()
    }

    fun onClearedTopRated(){
        movieTopRateBoundaryCallback!!.onCleared()
    }

    fun onClearedPopularMovies(){
        movieTopRateBoundaryCallback!!.onCleared()
    }

    private fun converter(movie: Movie): AdapterPopularMovieItem = AdapterPopularMovieItem(movie)
}