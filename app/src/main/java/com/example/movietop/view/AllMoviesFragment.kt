package com.example.movietop.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietop.databinding.FragmentAllBinding
import com.example.movietop.service.model.MovieModel
import com.example.movietop.view.adapter.MovieAdapter
import com.example.movietop.viewmodel.AllMoviesViewModel

class AllMoviesFragment : Fragment() {

    private lateinit var mViewModel: AllMoviesViewModel
    private lateinit var mAdapter: MovieAdapter

    private var _binding: FragmentAllBinding? = null
    private val binding: FragmentAllBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, saveInstStat: Bundle?
    ): View {
        mViewModel = ViewModelProvider(this).get(AllMoviesViewModel::class.java)

        _binding = FragmentAllBinding.inflate(inflater, container, false)

        mAdapter = MovieAdapter(mViewModel.movieList.value) { movieModel ->

            val movieId = movieModel.id
            val action = AllMoviesFragmentDirections.actionNavAllMoviesToMovieFragment(movieId)
            Navigation.findNavController(binding.root).navigate(action)
        }

        val recycler = binding.recyclerAllMovies
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        lifecycle.addObserver(mViewModel)

        observer()
        return binding.root
    }

    private fun observer() {
        mViewModel.movieList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateMovies(it)
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}