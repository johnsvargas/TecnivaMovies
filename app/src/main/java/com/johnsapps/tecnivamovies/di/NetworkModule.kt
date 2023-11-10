package com.johnsapps.tecnivamovies.di

import com.johnsapps.tecnivamovies.data.network.TMDBApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = TokenInterceptor()
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient(interceptor))
            .build()
    }

    @Singleton
    @Provides
    fun provideTMDBApiService(retrofit: Retrofit): TMDBApiClient {
        return retrofit.create(TMDBApiClient::class.java)
    }

    private fun okhttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            addInterceptor(buildOkHttpLoggerInterceptor())
        }.build()
    }

    private fun buildOkHttpLoggerInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            //if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
            //}
        }
    }

}