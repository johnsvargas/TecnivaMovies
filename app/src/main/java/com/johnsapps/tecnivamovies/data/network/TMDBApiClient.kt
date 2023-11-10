package com.johnsapps.tecnivamovies.data.network

import com.johnsapps.tecnivamovies.data.model.MovieNow
import com.johnsapps.tecnivamovies.data.model.ResponseMediaVideo
import com.johnsapps.tecnivamovies.data.model.TvSeriesNow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiClient {
    @GET("search/multi")
    suspend fun searchMovies(@Query("language") language:String="es-US", @Query("page") page:Int=1, @Query("query") query:String): ResponseMediaVideo

    @GET("movie/{id}")
    suspend fun getDetailMovie(@Path("id" ) id:Long, @Query("language") language:String="es-MX"): MovieNow

    @GET("tv/{id}")
    suspend fun getDetailTvSeries(@Path("id" ) id:Long, @Query("language") language:String="es-MX"): TvSeriesNow

}