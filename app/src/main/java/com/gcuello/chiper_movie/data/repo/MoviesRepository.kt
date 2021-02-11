package com.gcuello.chiper_movie.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gcuello.chiper_movie.data.datasource.MovieBoundaryCallback
import com.gcuello.chiper_movie.data.db.datasource.PopularMovieLocalDataSource
import com.gcuello.chiper_movie.data.db.entities.Movie
import com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters.AdapterGroupMovieView
import com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters.AdapterPopularMovieItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val pMovieLocalDs: PopularMovieLocalDataSource
) {
    private var movieBoundaryCallback: MovieBoundaryCallback? = null

    fun fetchPagedListPopularMovie(): LiveData<PagedList<AdapterPopularMovieItem>> {
        movieBoundaryCallback = MovieBoundaryCallback(pMovieLocalDs)
        val pagedListConfig = PagedList.Config.Builder()
            .setPrefetchDistance(150)
            .setEnablePlaceholders(true)
            .setPageSize(50)
            .build()
        val keyedPaged = pMovieLocalDs.pagedListPopularMovies().map(::converter)
        return LivePagedListBuilder(
            keyedPaged,
            pagedListConfig
        ).setBoundaryCallback(movieBoundaryCallback).build()
    }

    private fun converter(movie: Movie) : AdapterPopularMovieItem = AdapterPopularMovieItem(movie)
}