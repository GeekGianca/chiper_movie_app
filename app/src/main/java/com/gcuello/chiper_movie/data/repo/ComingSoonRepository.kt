package com.gcuello.chiper_movie.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gcuello.chiper_movie.core.ServiceGenerator
import com.gcuello.chiper_movie.data.apiservice.MovieClient
import com.gcuello.chiper_movie.data.datasource.ComingSoonDataSource
import com.gcuello.chiper_movie.data.datasource.ComingSoonDataSourceFactory
import com.gcuello.chiper_movie.data.db.entities.Movie
import com.gcuello.chiper_movie.data.model.NetworkState
import com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters.AdapterPopularMovieItem
import io.reactivex.disposables.CompositeDisposable

class ComingSoonRepository {
    companion object {
        private const val TAG = "CharacterPageRepository"
    }

    private val client = ServiceGenerator.createService(MovieClient::class.java)

    lateinit var comingSoonPageList: LiveData<PagedList<AdapterPopularMovieItem>>
    private lateinit var characterDataSourceFactory: ComingSoonDataSourceFactory<AdapterPopularMovieItem>

    fun fetchComingSoonList(
        disposable: CompositeDisposable,
        converter: (Movie) -> AdapterPopularMovieItem
    ): LiveData<PagedList<AdapterPopularMovieItem>> {
        characterDataSourceFactory = ComingSoonDataSourceFactory(client, disposable, converter)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(80)
            .build()
        comingSoonPageList = LivePagedListBuilder(characterDataSourceFactory, config).build()
        return comingSoonPageList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap(
            characterDataSourceFactory.comingSoonResultDataSource,
            ComingSoonDataSource<AdapterPopularMovieItem>::networkState
        )
    }
}