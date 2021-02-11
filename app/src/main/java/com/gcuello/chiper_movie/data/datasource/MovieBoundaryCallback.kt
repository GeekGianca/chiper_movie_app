package com.gcuello.chiper_movie.data.datasource

import android.util.Log
import androidx.paging.PagedList
import com.gcuello.chiper_movie.core.Common
import com.gcuello.chiper_movie.core.ServiceGenerator
import com.gcuello.chiper_movie.data.apiservice.MovieClient
import com.gcuello.chiper_movie.data.db.dao.MovieDao
import com.gcuello.chiper_movie.data.db.datasource.PopularMovieLocalDataSource
import com.gcuello.chiper_movie.data.db.entities.Movie
import com.gcuello.chiper_movie.data.mapper.SharedMapper
import com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters.AdapterPopularMovieItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

class MovieBoundaryCallback(private val movieLocalDs: PopularMovieLocalDataSource) :
    PagedList.BoundaryCallback<AdapterPopularMovieItem>() {
    private val disposable = CompositeDisposable()
    private val client: MovieClient = ServiceGenerator.createService(MovieClient::class.java)

    init {
        Common.PAGE = 1
        Common.PARAMS["page"] = "${Common.PAGE}"
    }

    companion object {
        private const val TAG = "MovieBoundaryCallback"
    }

    override fun onZeroItemsLoaded() {
        fetchPopularMovies()
    }

    override fun onItemAtFrontLoaded(itemAtFront: AdapterPopularMovieItem) {
    }

    override fun onItemAtEndLoaded(itemAtEnd: AdapterPopularMovieItem) {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        disposable.add(
            client.popularMovieList(Common.PARAMS)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    val listResults = SharedMapper.INSTANCE.toMovieList(it.results, "popular")
                    Log.d(TAG, "SIZE RESULT: ${listResults.size}")
                    movieLocalDs.savePopularMovies(*listResults.toTypedArray())
                    Common.PAGE = Common.PAGE + 1
                    Common.PARAMS["page"] = "${Common.PAGE}"
                }, {
                    // todo
                    Log.d(TAG, it.message, it)
                })
        )
    }

}