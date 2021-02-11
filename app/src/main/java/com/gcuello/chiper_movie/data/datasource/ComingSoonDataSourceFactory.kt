package com.gcuello.chiper_movie.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gcuello.chiper_movie.data.apiservice.MovieClient
import com.gcuello.chiper_movie.data.db.entities.Movie
import com.xwray.groupie.Item
import io.reactivex.disposables.CompositeDisposable

class ComingSoonDataSourceFactory<T : Item<*>>(
    private val client: MovieClient,
    private val disposable: CompositeDisposable,
    private val converter: (Movie) -> T
) : DataSource.Factory<Int, T>() {

    var comingSoonResultDataSource = MutableLiveData<ComingSoonDataSource<T>>()
    override fun create(): DataSource<Int, T> {
        val characterDataSource = ComingSoonDataSource(client, disposable, converter)
        comingSoonResultDataSource.postValue(characterDataSource)
        return characterDataSource
    }

    fun refresh() {
        comingSoonResultDataSource.value?.invalidate()
    }
}