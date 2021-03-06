package com.gcuello.chiper_movie.data.db.datasource

import android.util.Log
import com.gcuello.chiper_movie.data.db.ConfigDatabase
import com.gcuello.chiper_movie.data.db.entities.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(database: ConfigDatabase) {
    companion object {
        private const val TAG = "PopularMovieLocalDataSo"
    }

    private val dao = database.popularMovieDao()

    fun pagedListPopularMovies(type:String) = dao.selectMovies(type)
    fun pagedListTopRateMovies(type:String) = dao.selectMovies(type)

    fun saveMovies(vararg args: Movie) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                dao.insert(*args)
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
            }
        }
    }
}