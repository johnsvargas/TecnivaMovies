package com.johnsapps.tecnivamovies.data.repository

import com.johnsapps.tecnivamovies.data.dataSource.SearchMovieDataSourceRemote
import com.johnsapps.tecnivamovies.domain.model.TmdbItem
import javax.inject.Inject

class SearchMovieRepository @Inject constructor(
    private val searchMovieDataSource: SearchMovieDataSourceRemote
) {
    suspend fun searchMovie(query: String, page: Int): TmdbItem {
        return searchMovieDataSource.searchMovies(query, page)
    }
}