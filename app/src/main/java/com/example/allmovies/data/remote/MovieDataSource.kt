package com.example.allmovies.data.remote

import com.example.allmovies.data.model.movieList
import com.example.allmovies.data.model.movieVideo
import com.example.allmovies.repository.WebService
import com.example.allmovies.utils.AppConstants

class movieDataSource(private val webService:WebService) {

    suspend fun getMoviePopular(): movieList = webService.getMoviePopular(AppConstants.API_KEY)
    suspend fun getPlayingNowMovies(): movieList = webService.getPlayingNowMovies(AppConstants.API_KEY)
//    suspend fun getVideoMovies(): movieVideo = webService.getVideoMovies(AppConstants.API_KEY)


}