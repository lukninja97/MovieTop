package com.example.movietop.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movietop.service.model.MovieModel
import com.example.movietop.service.repository.MovieRepository
import io.reactivex.schedulers.Schedulers

class AllMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val mMovieRepository = MovieRepository(application)

    private val mMovieList = MutableLiveData<List<MovieModel>>()
    val movieList: LiveData<List<MovieModel>> = mMovieList

    @SuppressLint("CheckResult")
    fun load() {
        mMovieRepository.loadMovies()
            .subscribeOn(Schedulers.io())
            .subscribe({
                mMovieList.postValue(it.results)
            }, { e ->
                e.printStackTrace()
            }, {

            })
    }
}