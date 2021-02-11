package com.gcuello.chiper_movie.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gcuello.chiper_movie.data.datasource.DetailRemoteDataSource
import com.gcuello.chiper_movie.data.datasource.GenreRemoteDataSource
import com.gcuello.chiper_movie.data.db.ConfigDatabase
import com.gcuello.chiper_movie.data.db.datasource.DetailLocalDataSource
import com.gcuello.chiper_movie.data.db.datasource.GenreLocalDataSource
import com.gcuello.chiper_movie.data.db.datasource.MovieLocalDataSource
import com.gcuello.chiper_movie.data.repo.ComingSoonRepository
import com.gcuello.chiper_movie.data.repo.DetailRepository
import com.gcuello.chiper_movie.data.repo.GenreRepository
import com.gcuello.chiper_movie.data.repo.MoviesRepository
import com.gcuello.chiper_movie.presentation.viewModel.ComingSoonVViewModel
import com.gcuello.chiper_movie.presentation.viewModel.DashboardViewModel
import com.gcuello.chiper_movie.presentation.viewModel.DetailViewModel
import com.gcuello.chiper_movie.presentation.viewModel.SplashViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule(private val dbConfig: ConfigDatabase) : ViewModelProvider.Factory {

    @Provides
    fun databaseConfiguration(): ConfigDatabase = dbConfig

    @Provides
    fun remoteGenreDataSource(): GenreRemoteDataSource = GenreRemoteDataSource()

    @Provides
    fun localGenreDataSource(): GenreLocalDataSource = GenreLocalDataSource(databaseConfiguration())

    @Provides
    fun localMovieDataSource(): MovieLocalDataSource =
        MovieLocalDataSource(databaseConfiguration())

    @Provides
    fun localDetailMovieDataSource(): DetailLocalDataSource =
        DetailLocalDataSource(databaseConfiguration())

    @Provides
    fun remoteDetailMovieDataSource(): DetailRemoteDataSource =
        DetailRemoteDataSource()

    @Provides
    fun genreRepository(): GenreRepository =
        GenreRepository(localGenreDataSource(), remoteGenreDataSource())

    @Provides
    fun movieRepository(): MoviesRepository =
        MoviesRepository(localMovieDataSource())

    @Provides
    fun detailRepository(): DetailRepository =
        DetailRepository(localDetailMovieDataSource(), remoteDetailMovieDataSource())

    @Provides
    fun comingSoonRepository(): ComingSoonRepository = ComingSoonRepository()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(genreRepository()) as T
            }
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(movieRepository(), genreRepository()) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(detailRepository()) as T
            }
            modelClass.isAssignableFrom(ComingSoonVViewModel::class.java) -> {
                ComingSoonVViewModel(comingSoonRepository()) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}