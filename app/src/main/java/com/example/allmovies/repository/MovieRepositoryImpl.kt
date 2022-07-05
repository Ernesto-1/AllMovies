package com.example.allmovies.repository

import com.example.allmovies.data.model.movieList
import com.example.allmovies.data.model.movieVideo
import com.example.allmovies.data.remote.movieDataSource

//implementacion de MovieRepository
class MovieRepositoryImpl(private val dataSource: movieDataSource): MovieRepository {

    override suspend fun getMoviePopular(): movieList = dataSource.getMoviePopular()

    override suspend fun getPlayingNowMovies(): movieList = dataSource.getPlayingNowMovies()
//    override suspend fun getVideoMovies(id: Int): movieVideo = dataSource.getVideoMovies()
}