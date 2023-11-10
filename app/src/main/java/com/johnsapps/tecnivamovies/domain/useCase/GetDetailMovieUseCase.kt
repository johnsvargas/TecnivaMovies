package com.johnsapps.tecnivamovies.domain.useCase

import com.johnsapps.tecnivamovies.data.dataSource.DetailMovieDataSource
import com.johnsapps.tecnivamovies.data.model.MovieNow
import com.johnsapps.tecnivamovies.data.model.TvSeriesNow
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(
    private val repository: DetailMovieDataSource
) {
    suspend fun getDetailMovie(id:Long): MovieNow {
        return repository.getDetailMovie(id = id)
    }
    suspend fun getDetailTvSeries(id:Long): TvSeriesNow {
        return repository.getDetailTvSeries(id = id)
    }
}