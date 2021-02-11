package com.gcuello.chiper_movie.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.gcuello.chiper_movie.core.Common
import com.gcuello.chiper_movie.data.apiservice.MovieClient
import com.gcuello.chiper_movie.data.db.entities.Movie
import com.gcuello.chiper_movie.data.mapper.SharedMapper
import com.gcuello.chiper_movie.data.model.NetworkState
import com.xwray.groupie.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ComingSoonDataSource<T : Item<*>>(
    private val client: MovieClient,
    private val disposable: CompositeDisposable,
    private val converter: (Movie) -> T
) : PageKeyedDataSource<Int, T>() {

    private var page = 1

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    companion object {
        private const val TAG = "ComingSoonDataSource"
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {
        Common.setParams()
        Common.PARAMS["page"] = "$page"
        networkState.postValue(NetworkState.LOADING)
        disposable.add(
            client.upcomingMovieList(Common.PARAMS)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    val resultList = SharedMapper.INSTANCE.toMovieList(it.results, "up-coming")
                    callback.onResult(
                        resultList.map(converter),
                        null,
                        page + Common.POST_PER_PAGE
                    )
                    if (resultList.isEmpty()) {
                        networkState.postValue(NetworkState.EMPTY)
                    } else {
                        networkState.postValue(NetworkState.LOADED)
                    }
                }, {
                    networkState.postValue(NetworkState.ERROR)
                    Log.e(TAG, it.message, it)
                })
        )
    }


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        networkState.postValue(NetworkState.LOADING)
        Common.PARAMS["page"] = "${params.key}"
        disposable.add(
            client.upcomingMovieList(Common.PARAMS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val resultList = SharedMapper.INSTANCE.toMovieList(it.results, "up-coming")
                    if (it.total_pages >= params.key) {
                        callback.onResult(
                            resultList.map(converter),
                            params.key + Common.POST_PER_PAGE
                        )
                        networkState.postValue(NetworkState.LOADED)
                    } else {
                        networkState.postValue(NetworkState.END_OF_LIST)
                    }
                }, {
                    networkState.postValue(NetworkState.ERROR)
                    Log.e(TAG, it.message, it)
                })
        )
    }

}