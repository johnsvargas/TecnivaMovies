package com.johnsapps.tecnivamovies.domain.model

import com.johnsapps.tecnivamovies.ui.searchMovie.viewModel.uiModel.MediaVideoUI

data class MediaVideoItem(
    val id: Long,
    val mediaType: String,
    val name: String,
    val posterUrl: String
)

fun MediaVideoItem.map(): MediaVideoUI {
    return MediaVideoUI(
        id = this.id,
        mediaType = this.mediaType,
        name = this.name,
        posterUrl = this.posterUrl,
    )
}