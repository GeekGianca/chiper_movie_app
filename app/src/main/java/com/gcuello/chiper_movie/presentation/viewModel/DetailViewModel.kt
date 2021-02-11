package com.gcuello.chiper_movie.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gcuello.chiper_movie.data.db.entities.Cast
import com.gcuello.chiper_movie.data.db.entities.MovieDetail
import com.gcuello.chiper_movie.data.repo.DetailRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val detailRepo: DetailRepository) : ViewModel() {

    val observerErrorNetwork: LiveData<String> = detailRepo.observableErrorNetwork

    val observableMovieDetail: LiveData<MovieDetail> = detailRepo.observableMovieDetail

    val observableCastingDetail: LiveData<List<Cast>> = detailRepo.observableCastingDetail

    val observableErrorSaveDetail: LiveData<String> = detailRepo.observableErrorSaveDetail

    val observableGenres: LiveData<List<String>> = detailRepo.observableGenresMovieDetail

    fun fetchDetailMovieFromNetworkToLocal(movieId: Int) {
        detailRepo.fetchRemoteToLocalMovieDetail(movieId)
        detailRepo.fetchMovieDetail(movieId)
        detailRepo.fetchRemoteCredits(movieId)
    }

    fun fetchGenres(ids: String) {
        detailRepo.genresByMovie(
            ids.substring(0, ids.length - 1).split(",").toTypedArray().map { it.toInt() })
    }
}