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
import com.example.movietop.viewmodel.FavoriteViewModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class FavoriteFragment : Fragment() {

    private lateinit var mFavoriteViewModel: FavoriteViewModel
    private val mDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)

    private val args: FavoriteFragmentArgs by navArgs()

    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        observe()

        loadFavorite()
        return binding.root
    }

    private fun observe() {

        binding.imageFavorite.setOnClickListener {
            val favoriteId = mFavoriteViewModel.favorite.value!!
            if (mFavoriteViewModel.isFavorite(favoriteId.id)) {
                mFavoriteViewModel.desFavorite(favoriteId.id)
                binding.imageFavorite.setImageResource(R.drawable.ic_star_false)
                Toast.makeText(context, "Filme Desfavoritado", Toast.LENGTH_SHORT).show()
            } else {
                mFavoriteViewModel.favorite(favoriteId)
                binding.imageFavorite.setImageResource(R.drawable.ic_star_true)
                Toast.makeText(context, "Filme Favoritado", Toast.LENGTH_SHORT).show()
            }
        }

        mFavoriteViewModel.favorite.observe(viewLifecycleOwner, Observer {
            binding.apply {
                val date = SimpleDateFormat("yyyy-MM-dd").parse(it.data)
                setImage(it.fundo)
                textTitle.text = it.title
                textResumo.text = it.resumo
                textData.text = mDateFormat.format(date)
                textNota.text = it.nota
                textVotos.text = "(${it.votos})"
                if (mFavoriteViewModel.isFavorite(it.id)) {
                    binding.imageFavorite.setImageResource(R.drawable.ic_star_true)
                } else {
                    binding.imageFavorite.setImageResource(R.drawable.ic_star_false)
                }

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

    private fun loadFavorite() {
        mFavoriteViewModel.load(args.favoriteId)
    }

    private fun setImage(index: String) {
        val imgPoster = binding.imageTitle
        Picasso.get().load("https://image.tmdb.org/t/p/original$index").into(imgPoster)
        imgPoster.setAlpha(0.4f)
    }

}