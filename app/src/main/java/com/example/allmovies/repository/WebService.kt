package com.example.allmovies.repository

import com.example.allmovies.data.model.movieList
import com.example.allmovies.data.model.movieVideo
import com.example.allmovies.utils.AppConstants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("movie/popular")
    suspend fun getMoviePopular(@Query("api_key")apikey : String): movieList

    @GET("movie/now_playing")
    suspend fun getPlayingNowMovies(@Query("api_key")apikey : String): movieList

//    @GET("movie/id/videos")
//    suspend fun getVideoMovies(@Query("api_key")apikey : String): movieVideo



}



object RetrofitClient{
    val webservice by lazy {
        Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}