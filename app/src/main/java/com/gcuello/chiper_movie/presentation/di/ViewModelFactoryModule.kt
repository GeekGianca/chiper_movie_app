package com.gcuello.chiper_movie.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gcuello.chiper_movie.data.datasource.GenreRemoteDataSource
import com.gcuello.chiper_movie.data.db.ConfigDatabase
import com.gcuello.chiper_movie.data.db.datasource.GenreLocalDataSource
import com.gcuello.chiper_movie.data.db.datasource.PopularMovieLocalDataSource
import com.gcuello.chiper_movie.data.repo.GenreRepository
import com.gcuello.chiper_movie.data.repo.MoviesRepository
import com.gcuello.chiper_movie.presentation.viewModel.DashboardViewModel
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
    fun localMovieDataSource(): PopularMovieLocalDataSource =
        PopularMovieLocalDataSource(databaseConfiguration())

    @Provides
    fun genreRepository(): GenreRepository =
        GenreRepository(localGenreDataSource(), remoteGenreDataSource())

    @Provides
    fun movieRepository(): MoviesRepository =
        MoviesRepository(localMovieDataSource())

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(genreRepository()) as T
            }
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(movieRepository(), genreRepository()) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}