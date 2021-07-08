package com.example.movietop.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movietop.service.model.MovieModel
import com.example.movietop.service.repository.FavoriteRepository
import com.example.movietop.service.repository.MovieRepository
import io.reactivex.schedulers.Schedulers

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val mMovieRepository = MovieRepository(application)
    private val mFavoriteRepository = FavoriteRepository(application)

    private val mMovie = MutableLiveData<MovieModel>()
    var movie: LiveData<MovieModel> = mMovie

    @SuppressLint("CheckResult")
    fun load(id: Int){
        mMovieRepository.getMovie(id)
            .subscribeOn(Schedulers.io())
            .subscribe ({
                mMovie.postValue(it)
            },{
                e -> e.printStackTrace()
            }, {

            })
    }

    fun favorite(favorite: MovieModel){
        mFavoriteRepository.insertFavorite(favorite.insert())
    }
}