package com.example.movietop.view.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.example.movietop.R
import com.example.movietop.databinding.RowMovieBinding
import com.example.movietop.service.listener.MovieListener
import com.example.movietop.service.model.FavoriteModel
import com.example.movietop.service.model.MovieModel
import com.example.movietop.service.repository.MovieRepository
import com.squareup.picasso.Picasso

class MovieViewHolder(private val binding: RowMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieModel) {
        binding.textTitle.text = movie.title
        binding.textResumo.text = movie.resumo
    }

    fun setImage(index: String) {
        val imgPoster = binding.imageMovie //itemView.findViewById<ImageView>(R.id.image_movie)
        Picasso.get().load("https://image.tmdb.org/t/p/original$index").into(imgPoster)
    }
}