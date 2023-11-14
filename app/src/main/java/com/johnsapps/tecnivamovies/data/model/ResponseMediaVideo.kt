package com.johnsapps.tecnivamovies.data.model

import com.google.gson.annotations.SerializedName
import com.johnsapps.tecnivamovies.data.Constants
import com.johnsapps.tecnivamovies.domain.model.MediaVideoItem
import com.johnsapps.tecnivamovies.domain.model.TmdbItem

data class ResponseMediaVideo(
    val page: Long,
    val results: ArrayList<MediaVideo>,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("total_results")
    val totalResults: Long
)

fun ResponseMediaVideo.map(): TmdbItem {
    return TmdbItem(
        page = this.page,
        totalPages = this.totalPages,
        listMovie = this.results.map {
            it.map()
        }
    )

}

data class MediaVideo(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("genre_ids")
    val genreIDS: List<Long>,
    val id: Long,
    @SerializedName("media_type")
    var mediaType: String,
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: List<String>? = null,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String? = null,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Long,
    val adult: Boolean? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null
)

fun MediaVideo.map(): MediaVideoItem {
    return MediaVideoItem(
        id = this.id,
        mediaType = this.mediaType,
        name = when (this.mediaType) {
            Constants.Movie -> this.originalTitle.toString()
            Constants.Tv -> this.name.toString()
            else -> this.name.toString()
        },
        posterUrl = Constants.url + this.posterPath,
    )
}