package com.johnsapps.tecnivamovies.data.repository

import com.johnsapps.tecnivamovies.data.dataSource.DetailMovieDataSource
import com.johnsapps.tecnivamovies.data.model.MovieNow
import com.johnsapps.tecnivamovies.data.model.TvSeriesNow
import javax.inject.Inject

class DetailMovieRepository  @Inject constructor(
    private val dataSource: DetailMovieDataSource
) {
    suspend fun getDetailMovie(id:Long): MovieNow {
        return dataSource.getDetailMovie(id = id)
    }
    suspend fun getDetailTvSeries(id:Long): TvSeriesNow {
        return dataSource.getDetailTvSeries(id = id)
    }
}