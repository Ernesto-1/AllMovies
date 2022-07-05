package com.example.allmovies.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allmovies.core.BaseViewHolder
import com.example.allmovies.data.model.movie
import com.example.allmovies.databinding.MovieItemBinding

class MovieAdapter(private val moviesList: List<movie>, private val itemClickListener: OnMovieClickListener): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener{
        fun onMovieClick(movie:movie)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder = MoviesViewHolder(itemBinding, parent.context)

       // el click para cada pelicula
        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?:return@setOnClickListener
            itemClickListener.onMovieClick(moviesList[position])
        }
        return holder
    }

    //muestra en su posision cada pelicula
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MoviesViewHolder -> holder.bind(moviesList[position])
        }
    }


    override fun getItemCount(): Int = moviesList.size


    //pone la imagen a cada pelicula
    private inner class MoviesViewHolder(val binding: MovieItemBinding, val context: Context):
        BaseViewHolder<movie>(binding.root){
        override fun bind(item: movie) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.poster_path}").centerCrop().into(binding.imgMovie)
        }

    }
}













