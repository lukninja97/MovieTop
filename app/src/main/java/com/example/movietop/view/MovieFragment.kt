package com.example.movietop.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.movietop.R
import com.example.movietop.databinding.FragmentMovieBinding
import com.example.movietop.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso

class MovieFragment : Fragment() {

    private lateinit var mMovieViewModel: MovieViewModel

    private val args: MovieFragmentArgs by navArgs()

    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mMovieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        observe()

        loadMovie()
        return binding.root
    }

    private fun observe() {

        binding.imageFavorite.setOnClickListener {
            val favoriteId = mMovieViewModel.movie.value!!
            if (mMovieViewModel.isFavorite(favoriteId.id)) {
                mMovieViewModel.desFavorite(favoriteId.id)
                binding.imageFavorite.setImageResource(R.drawable.ic_star_false)
                Toast.makeText(context, "Filme Desfavoritado", Toast.LENGTH_SHORT).show()
            } else {
                mMovieViewModel.favorite(favoriteId)
                binding.imageFavorite.setImageResource(R.drawable.ic_star_true)
                Toast.makeText(context, "Filme Favoritado", Toast.LENGTH_SHORT).show()
            }
        }

        mMovieViewModel.movie.observe(viewLifecycleOwner, Observer {
            binding.apply {
                setImage(it.fundo)
                textTitle.text = it.title
                textResumo.text = it.resumo
                textData.text = it.data
                textNota.text = it.nota
                textVotos.text = "(${it.votos})"
                if (mMovieViewModel.isFavorite(it.id)) {
                    binding.imageFavorite.setImageResource(R.drawable.ic_star_true)
                } else {
                    binding.imageFavorite.setImageResource(R.drawable.ic_star_false)
                }
            }
        })
    }

    private fun loadMovie() {
        mMovieViewModel.load(args.movieId)
    }

    private fun setImage(index: String) {
        val imgPoster = binding.imageTitle //itemView.findViewById<ImageView>(R.id.image_movie)
        Picasso.get().load("https://image.tmdb.org/t/p/original$index").into(imgPoster)
    }

}