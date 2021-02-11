package com.gcuello.chiper_movie.data.datasource

import android.util.Log
import androidx.paging.PagedList
import com.gcuello.chiper_movie.core.Common
import com.gcuello.chiper_movie.core.ServiceGenerator
import com.gcuello.chiper_movie.data.apiservice.MovieClient
import com.gcuello.chiper_movie.data.db.datasource.MovieLocalDataSource
import com.gcuello.chiper_movie.data.mapper.SharedMapper
import com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters.AdapterPopularMovieItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieBoundaryCallback(
    private val movieLocalDs: MovieLocalDataSource,
    private val initialPage: Int
) :
    PagedList.BoundaryCallback<AdapterPopularMovieItem>() {
    private val disposable = CompositeDisposable()
    private val client: MovieClient = ServiceGenerator.createService(MovieClient::class.java)


    // Keep reference to what page we are on
    private var pageToRequest = initialPage

    // Avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    // Avoid requesting pages after all pages have been request
    private var allPagesGrabbed = false

    init {
        Common.PARAMS["page"] = "$pageToRequest"
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
        if (isRequestInProgress) return
        disposable.add(
            client.popularMovieList(Common.PARAMS)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    isRequestInProgress = true
                    val listResults = SharedMapper.INSTANCE.toMovieList(it.results, "popular")
                    Log.d(TAG, "SIZE RESULT: ${listResults.size}")
                    movieLocalDs.saveMovies(*listResults.toTypedArray())
                    Common.setParams()
                    pageToRequest++
                    Common.PARAMS["page"] = "$pageToRequest"
                    isRequestInProgress = false
                }, {
                    // todo
                    Log.d(TAG, it.message, it)
                })
        )
    }

}