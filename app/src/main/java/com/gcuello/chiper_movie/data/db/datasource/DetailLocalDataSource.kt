package com.gcuello.chiper_movie.data.db.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gcuello.chiper_movie.data.db.ConfigDatabase
import com.gcuello.chiper_movie.data.db.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailLocalDataSource @Inject constructor(database: ConfigDatabase) {
    private val castDao = database.castDao()
    private val crewDao = database.crewDao()
    private val genreDao = database.genreDao()
    private val movieDetailDao = database.movieDetailDao()
    private val creditDao = database.creditDao()

    companion object {
        private const val TAG = "DetailLocalDataSource"
    }

    val observableSave = MutableLiveData<String>()

    val observableFlowMovieDetail = MutableLiveData<MovieDetail>()

    val observableFlowCastingDetail = MutableLiveData<List<Cast>>()

    val observableListGenresMovie = MutableLiveData<List<String>>()

    val observableErrorSave = MutableLiveData<String>()

    fun selectGenresByMovie(ids: List<Int>) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val genres = genreDao.selectByIds(ids)
                observableListGenresMovie.postValue(genres)
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
            }
        }
    }

    fun selectMovieDetail(movieId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val flowDetail = movieDetailDao.selectDetailByMovie(movieId)
                flowDetail.collect { observableFlowMovieDetail.postValue(it) }
            } catch (e: Exception) {
                observableErrorSave.postValue(e.message)
                Log.e(TAG, e.message, e)
            }
        }
    }

    private fun selectCastByIds(castIds: List<Int>) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val casting = castDao.selectByIds(castIds)
                casting.collect {
                    observableFlowCastingDetail.postValue(it)
                }
            } catch (e: Exception) {
                observableErrorSave.postValue(e.message)
                Log.e(TAG, e.message, e)
            }
        }
    }

    fun selectCreditByMovie(movieId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val credits = creditDao.selectByIds(movieId)
                credits.collect {
                    if (it != null) {
                        val castList =
                            it.cast!!.split(",").toTypedArray().map { itm -> itm.toInt() }
                        selectCastByIds(castList)
                    }
                }
            } catch (e: Exception) {
                observableErrorSave.postValue(e.message)
                Log.e(TAG, e.message, e)
            }
        }
    }

    fun insert(args: Credit) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                creditDao.insert(args)
            } catch (e: Exception) {
                observableErrorSave.postValue(e.message)
                Log.e(TAG, e.message, e)
            }
        }
    }

    fun insert(vararg args: MovieDetail) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                movieDetailDao.insert(*args)
            } catch (e: Exception) {
                observableErrorSave.postValue(e.message)
                Log.e(TAG, e.message, e)
            }
        }
    }

    fun insert(vararg args: Cast) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                castDao.insert(*args)
            } catch (e: Exception) {
                observableErrorSave.postValue(e.message)
                Log.e(TAG, e.message, e)
            }
        }
    }

    fun insert(vararg args: Crew) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                crewDao.insert(*args)
            } catch (e: Exception) {
                observableErrorSave.postValue(e.message)
                Log.e(TAG, e.message, e)
            }
        }
    }
}