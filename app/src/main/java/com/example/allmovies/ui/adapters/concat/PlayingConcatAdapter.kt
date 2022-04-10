package com.example.allmovies.ui.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allmovies.core.BaseConcatHolder
import com.example.allmovies.databinding.PlayingnowMovieRowBinding
import com.example.allmovies.ui.adapters.MovieAdapter

class PlayingConcatAdapter(private val moviesadapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = PlayingnowMovieRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(moviesadapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")

        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: PlayingnowMovieRowBinding): BaseConcatHolder<MovieAdapter>(binding.root) {
        override fun bind(adapter: MovieAdapter) {
            binding.rvNowPlaying.adapter = adapter
        }
    }
}