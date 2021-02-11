package com.gcuello.chiper_movie.data.db.dao

import androidx.room.*
import com.gcuello.chiper_movie.data.db.entities.Genre

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg args: Genre)

    @Transaction
    @Query("SELECT name FROM genres WHERE id IN (:ids);")
    suspend fun selectByIds(ids: List<Int>): List<String>

    @Transaction
    @Query("SELECT * FROM genres")
    suspend fun selectAll(): List<Genre>
}