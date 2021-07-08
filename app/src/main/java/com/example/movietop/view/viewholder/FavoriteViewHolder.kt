package com.example.movietop.view.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.example.movietop.databinding.RowMovieBinding
import com.example.movietop.service.model.FavoriteModel
import com.squareup.picasso.Picasso

class FavoriteViewHolder(private val binding: RowMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(favorite: FavoriteModel) {
        binding.textTitle.text = favorite.title
        binding.textResumo.text = favorite.resumo
    }

    fun setImage(index: String) {
        val imgPoster = binding.imageMovie
        Picasso.get().load("https://image.tmdb.org/t/p/original$index").into(imgPoster)
    }
}