package com.example.movietop.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movietop.R
import com.example.movietop.service.model.MovieModel
import com.example.movietop.view.viewholder.MovieViewHolder

class MovieAdapter: RecyclerView.Adapter<MovieViewHolder>() {

    private var mMovieList: List<MovieModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false)
        return MovieViewHolder(item)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(mMovieList[position])
        holder.setImage(mMovieList[position].poster)
    }

    override fun getItemCount(): Int {
       return mMovieList.count()
    }

    fun updateMovies(list: List<MovieModel>){
        mMovieList = list
        notifyDataSetChanged()
    }
}