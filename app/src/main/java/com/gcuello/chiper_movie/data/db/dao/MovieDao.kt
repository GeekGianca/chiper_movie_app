package com.gcuello.chiper_movie.data.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.gcuello.chiper_movie.data.db.entities.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg args: Movie)

    @Transaction
    @Query("SELECT * FROM movies WHERE type =:type ORDER BY popularity DESC")
    fun selectMovies(type: String): DataSource.Factory<Int, Movie>

    @Transaction
    @Query("SELECT * FROM movies WHERE title = :title")
    fun selectByName(title: String): Movie
}