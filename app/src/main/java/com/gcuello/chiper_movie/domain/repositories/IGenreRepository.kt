package com.gcuello.chiper_movie.domain.repositories

interface IGenreRepository {
    fun fetchGenreByIds(args: List<Int>)
    fun fetchAllGenres()
    fun fetchMovieGenres()
    fun fetchTvGenres()
}