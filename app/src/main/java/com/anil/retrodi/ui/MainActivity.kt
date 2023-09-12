package com.anil.retrodi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.anil.retrodi.data.model.Movie
import com.anil.retrodi.databinding.ActivityMainBinding
import com.anil.retrodi.ui.adapters.MovieListAdapter
import com.anil.retrodi.ui.adapters.MovieListAdapterRuntime
import com.anil.retrodi.ui.viewModels.MainActivityViewModel
import dagger.assisted.AssistedFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var movieListAdapter: MovieListAdapter

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    // passing runtime parameter for adapater
    lateinit var movieListAdapterRuntime: MovieListAdapterRuntime

    @Inject
    lateinit var movieListFactory: MovieListFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            lifecycleScope.launch {
                mainActivityViewModel.getMovies().collect {
                    binding.rvMovie.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        /**
                         * passing runtime parameter to adapter
                         */
                        getMovieList(it)
                        adapter = movieListAdapterRuntime
//                        adapter=movieListAdapter
//                        movieListAdapter.movieList= it as MutableList<Movie>

                        binding.btn1.visibility= View.GONE
                    }
                }
            }
        }
    }

    fun getMovieList(movie: List<Movie>){
        movieListAdapterRuntime = movieListFactory.create(movie)
    }
}

@AssistedFactory
interface MovieListFactory{
    fun create(movie:List<Movie>):MovieListAdapterRuntime
}