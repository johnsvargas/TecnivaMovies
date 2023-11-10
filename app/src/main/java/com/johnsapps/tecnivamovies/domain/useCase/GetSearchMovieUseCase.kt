package com.johnsapps.tecnivamovies.domain.useCase

import com.johnsapps.tecnivamovies.data.repository.SearchMovieRepository
import javax.inject.Inject

class GetSearchMovieUseCase @Inject constructor(
    private val repository: SearchMovieRepository
) {
    suspend operator fun invoke(query: String, page: Int) = repository.searchMovie(query, page)
}