package com.example.allmovies.repository

import com.example.allmovies.data.model.movieList
import com.example.allmovies.data.model.movieVideo

//definicon de metodos con co-rutinas "suspend" no se ejecuta hasta que servidor responda
interface MovieRepository {

   suspend fun getMoviePopular(): movieList
   suspend fun getPlayingNowMovies(): movieList
//   suspend fun getVideoMovies(id: Int): movieVideo
}