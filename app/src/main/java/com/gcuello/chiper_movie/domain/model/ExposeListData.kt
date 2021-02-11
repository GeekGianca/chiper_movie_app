package com.gcuello.chiper_movie.domain.model

data class ExposeListData<T>(
    val dates:Date,
    val page: Int,
    val results: T,
    val total_pages: Int,
    val total_results: Int
)
