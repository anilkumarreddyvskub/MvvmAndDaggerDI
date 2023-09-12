package com.anil.retrodi.domain

import com.anil.retrodi.data.model.Movie

interface MovieUseCase {
    suspend fun fetchMovies(): List<Movie>
}