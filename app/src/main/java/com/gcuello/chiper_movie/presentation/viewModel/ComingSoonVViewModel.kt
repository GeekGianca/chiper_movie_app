package com.gcuello.chiper_movie.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.gcuello.chiper_movie.data.db.entities.Movie
import com.gcuello.chiper_movie.data.model.NetworkState
import com.gcuello.chiper_movie.data.repo.ComingSoonRepository
import com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters.AdapterPopularMovieItem
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ComingSoonVViewModel @Inject constructor(private val comingSoonRepo: ComingSoonRepository) :
    ViewModel() {
    private val disposable = CompositeDisposable()

    val networkCreatorObservable: LiveData<NetworkState> by lazy {
        comingSoonRepo.getNetworkState()
    }

    val observableComingSoon: LiveData<PagedList<AdapterPopularMovieItem>> by lazy {
        comingSoonRepo.fetchComingSoonList(disposable, ::converter)
    }

    private fun converter(movie: Movie): AdapterPopularMovieItem = AdapterPopularMovieItem(movie)
}