package com.example.allmovies.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.allmovies.R
import com.example.allmovies.core.Resource
import com.example.allmovies.data.model.movie
import com.example.allmovies.data.remote.movieDataSource
import com.example.allmovies.databinding.FragmentPrinipalBinding
import com.example.allmovies.presentation.MovieViewModel
import com.example.allmovies.presentation.MoviewViewModelFactory
import com.example.allmovies.repository.MovieRepositoryImpl
import com.example.allmovies.repository.RetrofitClient
import com.example.allmovies.ui.adapters.MovieAdapter
import com.example.allmovies.ui.adapters.concat.PlayingConcatAdapter
import com.example.allmovies.ui.adapters.concat.PopularConcatAdapter


class PrinipalFragment : Fragment(R.layout.fragment_prinipal), MovieAdapter.OnMovieClickListener {
    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var binding: FragmentPrinipalBinding
    private val viewModel by viewModels<MovieViewModel> { MoviewViewModelFactory(
        MovieRepositoryImpl(movieDataSource(RetrofitClient.webservice))) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrinipalBinding.bind(view)
        concatAdapter = ConcatAdapter()

        viewModel.fetchMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0,PopularConcatAdapter(MovieAdapter(
                            result.data.first.results,this@PrinipalFragment)))
                        addAdapter(1,PlayingConcatAdapter(MovieAdapter(
                            result.data.second.results,this@PrinipalFragment)))

                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("FetchError", "Error: $result.exception ")
                    Toast.makeText(requireContext(), "Error: ${result.exception}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    override fun onMovieClick(movie: movie) {
        val action = PrinipalFragmentDirections.actionPrinipalFragmentToDetailsFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.title,
            movie.original_language,
            movie.release_date,
            movie.overview,


            movie.vote_count,
            movie.id

        )
        findNavController().navigate(action)
    }


}