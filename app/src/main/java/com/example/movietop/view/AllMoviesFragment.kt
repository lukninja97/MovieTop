package com.example.movietop.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietop.R
import com.example.movietop.view.adapter.MovieAdapter
import com.example.movietop.viewmodel.AllMoviesViewModel

class AllMoviesFragment : Fragment() {

    private lateinit var allMoviesViewModel: AllMoviesViewModel
    private val mAdapter: MovieAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, saveInstStat: Bundle?
    ): View? {
        allMoviesViewModel = ViewModelProvider(this).get(AllMoviesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_movies)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        allMoviesViewModel.load()

        observer()

        return root
    }

    private fun observer() {
        allMoviesViewModel.movieList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateMovies(it)
        })
    }

}