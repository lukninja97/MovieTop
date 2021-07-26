package com.example.movietop.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.movietop.databinding.RowMovieBinding
import com.example.movietop.service.model.MovieModel
import com.example.movietop.view.AllMoviesFragmentDirections
import com.example.movietop.view.viewholder.MovieViewHolder

class MovieAdapter(private var movies: List<MovieModel>?, val clickListener: (MovieModel) -> Unit): RecyclerView.Adapter<MovieViewHolder>() {

    private var mMovieList: List<MovieModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(mMovieList[position], clickListener)
        holder.setImage(mMovieList[position].poster)
        /*holder.itemView.setOnClickListener{

            val movieId = mMovieList[position].id
            val action = AllMoviesFragmentDirections.actionNavAllMoviesToMovieFragment(movieId)
            Navigation.findNavController(holder.itemView).navigate(action)
        }*/
    }

    override fun getItemCount(): Int {
       return mMovieList.count()
    }

    fun updateMovies(list: List<MovieModel>){
        mMovieList = list
        notifyDataSetChanged()
    }

}