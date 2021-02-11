package com.gcuello.chiper_movie.domain.usecases

import kotlinx.coroutines.flow.Flow

interface SingleCrud<T> {

    fun create(vararg args: T)

    fun update(vararg args: T)

    suspend fun selectById(args: Any): T

    suspend fun selectAll(): List<T>

    fun selectByIdDistinctUntilChanged(args: Any): Flow<List<T>>

    fun selectAllDistinctUntilChanged(args: Any): Flow<List<T>>
}