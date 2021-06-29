package com.example.movietop.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movietop.R
import com.example.movietop.service.model.MovieModel
import com.example.movietop.service.repository.MovieRepository
import com.squareup.picasso.Picasso

class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    //private val mMovieRepository = MovieRepository(itemView.context)

    private var mImagePoster: ImageView = itemView.findViewById(R.id.image_movie)
    private var mTextTitle: TextView = itemView.findViewById(R.id.text_title)
    private var mTextResumo: TextView = itemView.findViewById(R.id.text_resumo)
    private var mImageFavorito: ImageView = itemView.findViewById(R.id.image_favorite)


    fun bind(movie: MovieModel){
        mTextTitle.text = movie.title
        mTextResumo.text = movie.resumo

        if (movie.favorite){
            mImageFavorito.setImageResource(R.drawable.ic_star_true)
        }else{
            mImageFavorito.setImageResource(R.drawable.ic_star_false)
        }

    }

    fun setImage(index: String){
        val imgPoster = itemView.findViewById<ImageView>(R.id.image_movie)
        Picasso.get().load("https://image.tmdb.org/t/p/original$index").into(imgPoster)
    }
}