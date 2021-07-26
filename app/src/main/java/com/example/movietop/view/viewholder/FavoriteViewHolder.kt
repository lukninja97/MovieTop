package com.example.movietop.view.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.example.movietop.databinding.RowMovieBinding
import com.example.movietop.service.model.FavoriteModel
import com.example.movietop.service.model.MovieModel
import com.squareup.picasso.Picasso

class FavoriteViewHolder(private val binding: RowMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(favorite: FavoriteModel, clickListener: (FavoriteModel) -> Unit) {
        binding.textTitle.text = favorite.title
        binding.textResumo.text = favorite.resumo

        binding.root.setOnClickListener { clickListener(favorite) }
    }

    fun setImage(index: String) {
        val imgPoster = binding.imageMovie
        Picasso.get().load("https://image.tmdb.org/t/p/original$index").into(imgPoster)
    }
}