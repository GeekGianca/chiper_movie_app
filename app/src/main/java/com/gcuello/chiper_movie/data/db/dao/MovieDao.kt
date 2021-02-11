package com.gcuello.chiper_movie.data.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.gcuello.chiper_movie.data.db.entities.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg args: Movie)

    @Transaction
    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun selectMovies(): DataSource.Factory<Int, Movie>
}