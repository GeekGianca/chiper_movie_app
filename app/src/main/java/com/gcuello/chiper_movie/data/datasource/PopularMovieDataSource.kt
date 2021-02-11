package com.gcuello.chiper_movie.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.gcuello.chiper_movie.core.Common
import com.gcuello.chiper_movie.core.Common.PAGE
import com.gcuello.chiper_movie.data.apiservice.MovieClient
import com.gcuello.chiper_movie.data.db.entities.Movie
import com.gcuello.chiper_movie.data.mapper.SharedMapper
import com.gcuello.chiper_movie.data.model.NetworkState
import com.xwray.groupie.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PopularMovieDataSource<T : Item<*>>(
    private val client: MovieClient,
    private val disposable: CompositeDisposable,
    private val converter: (Movie) -> T
) : PageKeyedDataSource<Int, T>() {

    private var page = PAGE
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, T>) {
        Common.PARAMS["page"] = "1"
        networkState.postValue(NetworkState.LOADING)
        disposable.add(
            client.popularMovieList(Common.PARAMS)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    val listResults = SharedMapper.INSTANCE.toMovieList(it.results, "popular_movies")
                    callback.onResult(listResults.map(converter), null, page + 1)
                    if (it.results.isEmpty()) {
                        networkState.postValue(NetworkState.EMPTY)
                    } else {
                        networkState.postValue(NetworkState.LOADED)
                    }
                }, {
                    networkState.postValue(NetworkState.ERROR)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        networkState.postValue(NetworkState.LOADING)
        Common.PARAMS["page"] = "${params.key}"
        disposable.add(
            client.popularMovieList(Common.PARAMS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.total_pages >= params.key) {
                        val listResults = SharedMapper.INSTANCE.toMovieList(it.results, "popular_movies")
                        callback.onResult(listResults.map(converter), params.key + 1)
                        networkState.postValue(NetworkState.LOADED)
                    } else {
                        networkState.postValue(NetworkState.END_OF_LIST)
                    }
                }, {
                    networkState.postValue(NetworkState.ERROR)
                })
        )
    }
}