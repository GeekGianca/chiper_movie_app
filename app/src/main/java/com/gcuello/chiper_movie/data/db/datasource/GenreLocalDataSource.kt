package com.gcuello.chiper_movie.data.db.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gcuello.chiper_movie.data.db.ConfigDatabase
import com.gcuello.chiper_movie.data.db.entities.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreLocalDataSource @Inject constructor(database: ConfigDatabase) {
    companion object{
        private const val TAG = "GenreLocalDataSource"
    }
    private val dao = database.genreDao()

    val observableGenreList = MutableLiveData<List<Genre>>()

    val observableGenreNameList = MutableLiveData<List<String>>()

    val observableErrorGenre = MutableLiveData<String>()

    val observableSaveGenre = MutableLiveData<String>()

    fun insertOrMultipleInsert(argsReturn: String, args: List<Genre>) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                dao.insert(*args.toTypedArray())
                observableSaveGenre.postValue(argsReturn)
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
                observableErrorGenre.postValue(e.message)
            }
        }
    }

    fun fetchGenreByIds(args: List<Int>) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val list = dao.selectByIds(args)
                observableGenreNameList.postValue(list)
            } catch (e: Exception) {
                observableErrorGenre.postValue(e.message)
            }
        }
    }

    fun fetchAllGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val list = dao.selectAll()
                observableGenreList.postValue(list)
            } catch (e: Exception) {
                observableErrorGenre.postValue(e.message)
            }
        }
    }
}