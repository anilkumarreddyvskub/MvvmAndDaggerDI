package com.anil.retrodi.ui.viewModels

import androidx.lifecycle.ViewModel
import com.anil.retrodi.domain.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val movieUseCase: MovieUseCase):ViewModel(){

    fun getMovies() = flow {
        val result = movieUseCase.fetchMovies()
        emit(result)
    }
}