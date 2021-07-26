package com.example.movietop.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietop.databinding.FragmentAllFavoritesBinding
import com.example.movietop.view.adapter.FavoriteAdapter
import com.example.movietop.view.adapter.MovieAdapter
import com.example.movietop.viewmodel.AllFavoritesViewModel

class AllFavoritesFragment : Fragment() {

    private lateinit var mViewModel: AllFavoritesViewModel
    private lateinit var mAdapter: FavoriteAdapter

    private var _binding: FragmentAllFavoritesBinding? = null
    private val binding: FragmentAllFavoritesBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, saveInstStat: Bundle?
    ): View {
        mViewModel = ViewModelProvider(this).get(AllFavoritesViewModel::class.java)

        _binding = FragmentAllFavoritesBinding.inflate(inflater, container, false)

        mAdapter = FavoriteAdapter(mViewModel.favoriteList.value) { movieModel ->

            val movieId = movieModel.id
            val action = AllFavoritesFragmentDirections.actionAllFavoritesFragmentToMovieFragment(movieId)
            Navigation.findNavController(binding.root).navigate(action)
        }

        val recycler = binding.recyclerAllFavorites
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        lifecycle.addObserver(mViewModel)

        observer()

        return binding.root
    }

    private fun observer() {
        mViewModel.favoriteList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateFavorites(it)
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}