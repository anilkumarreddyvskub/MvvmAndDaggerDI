package com.anil.retrodi.data.remote

import com.anil.retrodi.data.model.Movie
import retrofit2.http.GET

interface MovieApi {

    @GET("movielist.json")
    suspend fun getAllMovies():List<Movie>
}