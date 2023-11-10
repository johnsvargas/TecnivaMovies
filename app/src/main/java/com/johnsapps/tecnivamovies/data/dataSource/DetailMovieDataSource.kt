package com.johnsapps.tecnivamovies.data.dataSource

import com.johnsapps.tecnivamovies.data.model.MovieNow
import com.johnsapps.tecnivamovies.data.model.TvSeriesNow
import com.johnsapps.tecnivamovies.data.network.TMDBApiClient
import javax.inject.Inject

class DetailMovieDataSource @Inject constructor(
    private val apiClient: TMDBApiClient
) {
    suspend fun getDetailMovie(id:Long): MovieNow {
        return apiClient.getDetailMovie(id = id)
    }
    suspend fun getDetailTvSeries(id:Long): TvSeriesNow {
        return apiClient.getDetailTvSeries(id = id)
    }
}