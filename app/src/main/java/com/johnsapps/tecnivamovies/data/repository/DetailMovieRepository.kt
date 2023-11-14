package com.johnsapps.tecnivamovies.data.repository

import com.johnsapps.tecnivamovies.data.dataSource.DetailMovieDataSourceRemote
import com.johnsapps.tecnivamovies.domain.model.VideoItem
import javax.inject.Inject

class DetailMovieRepository @Inject constructor(
    private val dataSource: DetailMovieDataSourceRemote
) {
    suspend fun getVideoDetail(id: Long, typeOfVide: String): VideoItem {
        return dataSource.getDetailVideo(id = id, typeOfVide = typeOfVide)
    }
}