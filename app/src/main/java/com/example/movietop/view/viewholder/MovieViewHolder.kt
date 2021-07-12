package com.example.movietop.view.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.example.movietop.databinding.RowMovieBinding
import com.example.movietop.service.model.MovieModel
import com.squareup.picasso.Picasso

class MovieViewHolder(private val binding: RowMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieModel) {
        binding.textTitle.text = movie.title
        binding.textResumo.text = movie.resumo
    }

    fun setImage(index: String) {
        val imgPoster = binding.imageMovie
        Picasso.get().load("https://image.tmdb.org/t/p/original$index").into(imgPoster)
    }
}