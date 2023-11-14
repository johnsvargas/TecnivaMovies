package com.johnsapps.tecnivamovies.domain.useCase

import com.johnsapps.tecnivamovies.data.repository.DetailMovieRepository
import com.johnsapps.tecnivamovies.domain.model.VideoItem
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(
    private val repository: DetailMovieRepository
) {
    suspend fun getDetailMovie(id: Long, typeOfVide: String): VideoItem {
        return repository.getVideoDetail(id = id, typeOfVide = typeOfVide)
    }
}