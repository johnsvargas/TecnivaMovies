package com.johnsapps.tecnivamovies.data.dataSource

import com.johnsapps.tecnivamovies.data.model.map
import com.johnsapps.tecnivamovies.data.network.TMDBApiClient
import com.johnsapps.tecnivamovies.domain.model.TmdbItem
import javax.inject.Inject

class SearchMovieDataSourceRemote @Inject constructor(
    private val apiClient: TMDBApiClient
) {
    suspend fun searchMovies(query: String, page: Int): TmdbItem {
        return apiClient.searchMovies(query = query, page = page).map()
    }
}