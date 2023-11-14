package com.johnsapps.tecnivamovies.domain.model

import com.johnsapps.tecnivamovies.ui.detailMovie.viewModel.uiModel.VideoUI

data class VideoItem(
    val id: Long,
    val title: String,
    val originalTitle: String,
    val urlPoster: String,
    val voteAverage: Double,
    val description: String,
    val genres: List<String>,
    val originalLanguage: String,
    val runtime: Long,
    val homepage: String,
)

fun VideoItem.map(): VideoUI {
    var genresText = ""
    this.genres.forEach {
        genresText += if (genresText.isEmpty()) it else String.format(", $it")
    }
    return VideoUI(
        title = this.title,
        originalTitle = this.originalTitle,
        urlPoster = this.urlPoster,
        voteAverage = this.voteAverage.toString(),
        description = this.description,
        genres = genresText,
        originalLanguage = this.originalLanguage,
        runtime = this.runtime.toString() + "min.",
        homepage = this.homepage,
    )
}