package com.johnsapps.tecnivamovies.data.model

import com.google.gson.annotations.SerializedName

data class MovieNow (
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection,

    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,

    @SerializedName("imdb_id")
    val imdbID: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,

    @SerializedName("release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long,

    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Long
)

data class BelongsToCollection (
    val id: Long,
    val name: String,

    @SerializedName("poster_path")
    val posterPath: Any? = null,

    @SerializedName("backdrop_path")
    val backdropPath: Any? = null
)

data class Genre (
    val id: Long,
    val name: String
)

data class ProductionCompany (
    val id: Long,

    @SerializedName("logo_path")
    val logoPath: String? = null,

    val name: String,

    @SerializedName("origin_country")
    val originCountry: String
)