package com.gcuello.chiper_movie.data.db.dao

import androidx.room.*
import com.gcuello.chiper_movie.data.db.entities.Cast
import kotlinx.coroutines.flow.Flow

@Dao
interface CastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg args: Cast)

    @Transaction
    @Query("SELECT * FROM `cast` WHERE id IN (:ids);")
    fun selectByIds(ids: List<Int>): Flow<List<Cast>>

    @Transaction
    @Query("SELECT * FROM `cast`")
    suspend fun selectAll(): List<Cast>
}