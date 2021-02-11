package com.gcuello.chiper_movie.data.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.gcuello.chiper_movie.data.db.entities.Movie

class PopularMoviesRemoteDataSource {
    private var userBoundaryCallback: MovieBoundaryCallback? = null
    var userList: LiveData<PagedList<Movie>>? = null

    init {

    }
}