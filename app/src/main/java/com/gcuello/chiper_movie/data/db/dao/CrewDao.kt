package com.gcuello.chiper_movie.data.db.dao

import androidx.room.*
import com.gcuello.chiper_movie.data.db.entities.Crew

@Dao
interface CrewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg args: Crew)

    @Transaction
    @Query("SELECT * FROM crew WHERE id IN (:ids);")
    suspend fun selectByIds(ids: List<Int>): List<Crew>

    @Transaction
    @Query("SELECT * FROM crew")
    suspend fun selectAll(): List<Crew>
}