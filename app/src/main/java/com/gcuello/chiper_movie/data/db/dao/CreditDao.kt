package com.gcuello.chiper_movie.data.db.dao

import androidx.room.*
import com.gcuello.chiper_movie.data.db.entities.Credit
import kotlinx.coroutines.flow.Flow

@Dao
interface CreditDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg args: Credit)

    @Transaction
    @Query("SELECT * FROM credits WHERE id =:movieId;")
    fun selectByIds(movieId: Int): Flow<Credit>
}