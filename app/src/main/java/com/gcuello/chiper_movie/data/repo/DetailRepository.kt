package com.gcuello.chiper_movie.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gcuello.chiper_movie.core.Common
import com.gcuello.chiper_movie.data.datasource.DetailRemoteDataSource
import com.gcuello.chiper_movie.data.db.datasource.DetailLocalDataSource
import com.gcuello.chiper_movie.data.db.entities.Cast
import com.gcuello.chiper_movie.data.db.entities.Credit
import com.gcuello.chiper_movie.data.db.entities.MovieDetail
import com.gcuello.chiper_movie.data.mapper.SharedMapper
import com.gcuello.chiper_movie.domain.model.Genres
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepository @Inject constructor(
    private val detailLocalDs: DetailLocalDataSource,
    private val detailRemoteDs: DetailRemoteDataSource
) {
    private val disposable = CompositeDisposable()

    val observableMovieDetail: LiveData<MovieDetail> = detailLocalDs.observableFlowMovieDetail

    val observableCastingDetail: LiveData<List<Cast>> = detailLocalDs.observableFlowCastingDetail

    val observableErrorNetwork = MutableLiveData<String>()

    val observableErrorSaveDetail: LiveData<String> = detailLocalDs.observableErrorSave

    val observableGenresMovieDetail: LiveData<List<String>> = detailLocalDs.observableListGenresMovie

    fun genresByMovie(ids: List<Int>) {
        detailLocalDs.selectGenresByMovie(ids)
    }

    fun fetchMovieDetail(movieId: Int) {
        detailLocalDs.selectMovieDetail(movieId)
        detailLocalDs.selectCreditByMovie(movieId)
    }

    fun fetchRemoteToLocalMovieDetail(movieId: Int) {
        Common.setParams()
        disposable.add(
            detailRemoteDs.detailsMovie(movieId, Common.PARAMS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val movieDetail = SharedMapper.INSTANCE.toEntity(it)
                    detailLocalDs.insert(movieDetail)
                }, {
                    observableErrorNetwork.value = it.message
                })
        )
    }

    fun fetchRemoteCredits(movieId: Int) {
        Common.setParams()
        disposable.add(
            detailRemoteDs.credits(movieId, Common.PARAMS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val castList = SharedMapper.INSTANCE.toList(it.cast)
                    val crewList = SharedMapper.INSTANCE.toCrewList(it.crew)
                    val credit = Credit(
                        it.id,
                        castList.map { idCast -> idCast.id }.joinToString(separator = ","),
                        crewList.map { idCrew -> idCrew.id }.joinToString(separator = ",")
                    )
                    detailLocalDs.insert(*castList.toTypedArray())
                    detailLocalDs.insert(*crewList.toTypedArray())
                    detailLocalDs.insert(credit)
                }, {
                    observableErrorNetwork.value = it.message
                })
        )
    }


}