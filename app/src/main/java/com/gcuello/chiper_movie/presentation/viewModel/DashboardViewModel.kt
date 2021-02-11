package com.gcuello.chiper_movie.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.gcuello.chiper_movie.core.LoadingState
import com.gcuello.chiper_movie.data.repo.GenreRepository
import com.gcuello.chiper_movie.data.repo.MoviesRepository
import com.gcuello.chiper_movie.presentation.ui.home.dashboard.adapters.AdapterPopularMovieItem
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


class DashboardViewModel @Inject constructor(
    private val movieRepo: MoviesRepository,
    private val genreRepo: GenreRepository
) :
    ViewModel() {
    private val loadingStateBehaviorSubject:BehaviorSubject<LoadingState>
    = BehaviorSubject.create<LoadingState>()

    val observerGenresNames: LiveData<List<String>> = genreRepo.observableGenresNameList

    fun findGenresHome(args: List<String>) {
        genreRepo.fetchGenreByIds(args.map { v -> v.toInt() })
    }

    val observerPopularMoviePagedList: LiveData<PagedList<AdapterPopularMovieItem>> by lazy {
        movieRepo.fetchPagedListPopularMovie()
    }

    val observerTopRatePagedList: LiveData<PagedList<AdapterPopularMovieItem>> by lazy {
        movieRepo.fetchPagedListTopRateMovie()
    }
}