package com.johnsapps.tecnivamovies.data.dataSource

import com.johnsapps.tecnivamovies.data.model.ResponseMediaVideo
import com.johnsapps.tecnivamovies.data.network.TMDBApiClient
import javax.inject.Inject

class SearchMovieDataSourceRemote @Inject constructor(
    private val apiClient: TMDBApiClient
) {
    suspend fun searchMovies(query: String, page: Int): ResponseMediaVideo {
        return apiClient.searchMovies(query= query, page =  page)
    }
}