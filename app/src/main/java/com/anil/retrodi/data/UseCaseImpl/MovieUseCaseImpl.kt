package com.anil.retrodi.data.UseCaseImpl

import com.anil.retrodi.data.model.Movie
import com.anil.retrodi.data.remote.MovieApi
import com.anil.retrodi.domain.MovieUseCase
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(private val movieApi: MovieApi):MovieUseCase {
    override suspend fun fetchMovies(): List<Movie> {
        return movieApi.getAllMovies()
    }

}