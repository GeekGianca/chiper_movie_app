package com.gcuello.chiper_movie.data.db.dao

import androidx.room.*
import com.gcuello.chiper_movie.data.db.entities.MovieDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg args: MovieDetail)

    @Transaction
    @Query("SELECT * FROM movie_detail WHERE id = :id;")
    fun selectDetailByMovie(id: Int): Flow<MovieDetail>


    @Transaction
    @Query("SELECT * FROM movie_detail")
    suspend fun selectAll(): List<MovieDetail>
}