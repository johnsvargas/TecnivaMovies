package com.johnsapps.tecnivamovies.data.dataSource

import com.johnsapps.tecnivamovies.data.Constants
import com.johnsapps.tecnivamovies.data.model.map
import com.johnsapps.tecnivamovies.data.network.TMDBApiClient
import com.johnsapps.tecnivamovies.domain.model.VideoItem
import javax.inject.Inject

class DetailMovieDataSourceRemote @Inject constructor(
    private val apiClient: TMDBApiClient
) {

    suspend fun getDetailVideo(id: Long, typeOfVide: String): VideoItem {
        return if (typeOfVide == Constants.Movie) {
            apiClient.getDetailMovie(id = id).map()
        } else {
            apiClient.getDetailTvSeries(id = id).map()
        }
    }
}