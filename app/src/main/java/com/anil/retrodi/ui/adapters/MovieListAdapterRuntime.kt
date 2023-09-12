package com.anil.retrodi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anil.retrodi.R
import com.anil.retrodi.data.model.Movie
import com.anil.retrodi.databinding.ItemLayoutBinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class MovieListAdapterRuntime @AssistedInject constructor(@Assisted val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieListAdapterVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapterVH {
        val binding = DataBindingUtil.inflate<ItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_layout, parent, false
        )
        return MovieListAdapterVH(binding)
    }

    override fun onBindViewHolder(holder: MovieListAdapterVH, position: Int) {
        holder.binding.movie = movieList[position]
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MovieListAdapterVH(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)