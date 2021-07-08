package com.example.movietop.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.movietop.databinding.FragmentMovieBinding
import com.example.movietop.service.listener.MovieListener
import com.example.movietop.viewmodel.FavoriteViewModel
import com.squareup.picasso.Picasso

class FavoriteFragment : Fragment() {

    private lateinit var mViewModel: FavoriteViewModel
    private lateinit var mListener: MovieListener

    private val args: FavoriteFragmentArgs by navArgs()

    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        observe()

        loadFavorite()
        return binding.root
    }

    private fun observe() {

        mViewModel.favorite.observe(viewLifecycleOwner, Observer {
            binding.apply {
                setImage(it.fundo)
                textTitle.text = it.title
                textResumo.text = it.resumo
                textData.text = it.data
                textNota.text = it.nota
                textVotos.text = "(${it.votos})"
            }
        })

    }

    private fun loadFavorite() {
        mViewModel.load(args.favoriteId)
    }

    private fun setImage(index: String) {
        val imgPoster = binding.imageTitle
        Picasso.get().load("https://image.tmdb.org/t/p/original$index").into(imgPoster)
    }

}