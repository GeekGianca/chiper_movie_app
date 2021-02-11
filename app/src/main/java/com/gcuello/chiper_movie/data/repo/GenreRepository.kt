package com.gcuello.chiper_movie.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gcuello.chiper_movie.core.Common
import com.gcuello.chiper_movie.data.datasource.GenreRemoteDataSource
import com.gcuello.chiper_movie.data.db.datasource.GenreLocalDataSource
import com.gcuello.chiper_movie.data.db.entities.Genre
import com.gcuello.chiper_movie.data.mapper.SharedMapper
import com.gcuello.chiper_movie.domain.model.Genres
import com.gcuello.chiper_movie.domain.repositories.IGenreRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRepository @Inject constructor(
    private val localGenreDs: GenreLocalDataSource,
    private val remoteGenreDs: GenreRemoteDataSource
) : IGenreRepository {

    private val disposable = CompositeDisposable()

    val observableNetworkErrorCaching = MutableLiveData<String>()

    val observableGenresNameList: LiveData<List<String>> = localGenreDs.observableGenreNameList

    val observableAllGenresList: LiveData<List<Genre>> = localGenreDs.observableGenreList

    val observableErrorLocal: LiveData<String> = localGenreDs.observableErrorGenre

    val observableSaveLocal: LiveData<String> = localGenreDs.observableSaveGenre

    override fun fetchGenreByIds(args: List<Int>) {
        localGenreDs.fetchGenreByIds(args)
    }

    override fun fetchAllGenres() {
        localGenreDs.fetchAllGenres()
    }

    override fun fetchMovieGenres() {
        Common.setParams()
        disposable.add(
            remoteGenreDs.genreMovieList(Common.PARAMS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Genres>() {
                    override fun onSuccess(t: Genres) {
                        val listGenreMapper = SharedMapper.INSTANCE.toList(t.genres, "movie")
                        localGenreDs.insertOrMultipleInsert(
                            "movie",
                            listGenreMapper
                        )
                    }

                    override fun onError(e: Throwable) {
                        observableNetworkErrorCaching.value = e.message
                    }
                })
        )
    }

    override fun fetchTvGenres() {
        Common.setParams()
        disposable.add(
            remoteGenreDs.genreTvList(Common.PARAMS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Genres>() {
                    override fun onSuccess(t: Genres) {
                        val listGenreMapper = SharedMapper.INSTANCE.toList(t.genres, "tv")
                        localGenreDs.insertOrMultipleInsert("tv", listGenreMapper)
                    }

                    override fun onError(e: Throwable) {
                        observableNetworkErrorCaching.value = e.message
                    }
                })
        )
    }

}