package com.example.movietop.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.movietop.R
import com.example.movietop.databinding.FragmentMovieBinding
import com.example.movietop.service.model.MovieModel
import com.example.movietop.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class MovieFragment : Fragment() {

    private lateinit var mMovieViewModel: MovieViewModel
    private val mDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)

    private val args: MovieFragmentArgs by navArgs()
    private var isFavorite = false
    private lateinit var movie : MovieModel

    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mMovieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        observe()

        loadMovie()
        return binding.root
    }

    private fun observe() {

        binding.imageFavorite.setOnClickListener {
            if (isFavorite){
                isFavorite = false
                mMovieViewModel.desFavorite(args.movieId)
                setFavorite(isFavorite)
                Toast.makeText(context, "Filme Desfavoritado", Toast.LENGTH_SHORT).show()
            }else{
                mMovieViewModel.favorite(movie)
                isFavorite = true
                setFavorite(isFavorite)
                Toast.makeText(context, "Filme Favoritado", Toast.LENGTH_SHORT).show()
            }
        }

        mMovieViewModel.isFav.observe(viewLifecycleOwner, {
            setFavorite(it)
        })

        mMovieViewModel.movie.observe(viewLifecycleOwner, {
            binding.apply {
                val date = SimpleDateFormat("yyyy-MM-dd").parse(it.data)
                setImage(it.fundo)
                textTitle.text = it.title
                textResumo.text = it.resumo
                textData.text = mDateFormat.format(date)
                textNota.text = it.nota
                textVotos.text = "(${it.votos})"
                movie = it
                mMovieViewModel.isFavorite(it.id)

                if(it.nota > "8"){
                    imageNota.setImageResource(R.drawable.ic_nota)
                } else if (it.nota > "7"){
                    imageNota.setImageResource(R.drawable.ic_nota_media)
                } else{
                    imageNota.setImageResource(R.drawable.ic_nota_baixa)
                }
            }
        })
    }

    private fun setFavorite(id: Boolean){
        if (id) {
            binding.imageFavorite.setImageResource(R.drawable.ic_star_true)
        } else {
            binding.imageFavorite.setImageResource(R.drawable.ic_star_false)
        }
        isFavorite = id
    }

    private fun loadMovie() {
        mMovieViewModel.load(args.movieId)
    }

    private fun setImage(index: String) {
        val imgPoster = binding.imageTitle
        Picasso.get().load("https://image.tmdb.org/t/p/original$index").into(imgPoster)
        imgPoster.setAlpha(0.4f)
    }

}