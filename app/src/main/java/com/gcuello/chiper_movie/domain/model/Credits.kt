package com.gcuello.chiper_movie.domain.model

data class Credits(
    val id:Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)
