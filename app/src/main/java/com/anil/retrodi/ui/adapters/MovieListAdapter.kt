package com.anil.retrodi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anil.retrodi.R
import com.anil.retrodi.data.model.Movie
import com.anil.retrodi.data.remote.MovieApi
import com.anil.retrodi.databinding.ItemLayoutBinding
import javax.inject.Inject

class MovieListAdapter @Inject constructor():RecyclerView.Adapter<MovieListViewHolder>() {

    var movieList = mutableListOf<Movie>()

    fun setMovies(movies:List<Movie>){
        this.movieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding = DataBindingUtil.inflate<ItemLayoutBinding>(LayoutInflater.from(parent.context),
            R.layout.item_layout,parent,false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.binding.movie = movieList[position]
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}

class MovieListViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)