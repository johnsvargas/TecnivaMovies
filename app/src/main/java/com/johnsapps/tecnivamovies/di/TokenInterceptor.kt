package com.johnsapps.tecnivamovies.di

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class TokenInterceptor : Interceptor {
    private val token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMDIzOWRjZGMwODc0NmI3MTI4MDAzOTZjYjI4ZWVmNyIsInN1YiI6IjYwZjZlNjAzYzQzOWMwMDA1YzI5NTIyMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MLfWvoAVljf3yBXVsm7Zp5vbbZf1d6Kko0nxDQR1uS0"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        //rewrite the request to add bearer token
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(newRequest)
    }
}