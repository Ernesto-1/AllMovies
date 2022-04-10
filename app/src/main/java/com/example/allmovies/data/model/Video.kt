package com.example.allmovies.data.model

data class Video(
    val iso_639_1: String = "",
    val iso_3136_1: String = "",
    val name: String = "",
    val key: String = "",
    val site : String = "",
    val size: Int = -1,
    val type: String = "",
    val official: Boolean = false,
    val published_at: String = "",
    val id: String = ""
)

data class movieVideo(val results: List<Video> = listOf())