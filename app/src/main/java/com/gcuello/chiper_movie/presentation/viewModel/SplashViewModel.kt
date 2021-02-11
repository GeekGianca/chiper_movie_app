package com.gcuello.chiper_movie.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gcuello.chiper_movie.data.repo.GenreRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val repoGenre: GenreRepository) : ViewModel() {

    val observableSaveSuccess: LiveData<String> by lazy {
        repoGenre.observableSaveLocal
    }

    fun observerErrorRefresh(): LiveData<String> = repoGenre.observableNetworkErrorCaching

    fun refreshMoviesGenresLocal() {
        repoGenre.fetchMovieGenres()
    }

    fun refreshTvGenresLocal() {
        repoGenre.fetchTvGenres()
    }
}