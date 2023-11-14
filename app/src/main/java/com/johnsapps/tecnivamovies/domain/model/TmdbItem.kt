package com.johnsapps.tecnivamovies.domain.model

data class TmdbItem(
    val page: Long,
    val totalPages: Long,
    val listMovie: List<MediaVideoItem>
)