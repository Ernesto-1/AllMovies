package com.example.allmovies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.allmovies.core.Resource
import com.example.allmovies.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo: MovieRepository): ViewModel() {

    //Usamos los estados de carga
    fun fetchMovies() = liveData(viewModelScope.coroutineContext + Dispatchers.Main){
        emit(Resource.Loading())

        try {
            emit(Resource.Success(Pair(repo.getMoviePopular(),repo.getPlayingNowMovies())))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}

//Crear instancia de repo
class MoviewViewModelFactory(private val repo:MovieRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }

}