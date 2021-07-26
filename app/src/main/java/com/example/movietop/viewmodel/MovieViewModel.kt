package com.example.movietop.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.movietop.service.model.MovieModel
import com.example.movietop.service.repository.FavoriteRepository
import com.example.movietop.service.repository.MovieRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val mMovieRepository = MovieRepository(application)
    private val mFavoriteRepository = FavoriteRepository(application)

    private val mMovie = MutableLiveData<MovieModel>()
    var movie: LiveData<MovieModel> = mMovie

    private val mIsFav = MutableLiveData<Boolean>()
    var isFav: LiveData<Boolean> = mIsFav

    private val composeDisposable = CompositeDisposable()

    fun load(id: Int) {
        composeDisposable.add(
            mMovieRepository.getMovie(id)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    mMovie.postValue(it)
                }, { e ->
                    e.printStackTrace()
                }, {

                })
        )
    }

    fun favorite(favorite: MovieModel) {
        composeDisposable.add(
            mFavoriteRepository.insertFavorite(favorite.insert())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    Log.d("favorite", "completed")
                },
                    { t ->
                        Throwable(t)
                    })
        )
    }

    fun desFavorite(id: Int) {
        composeDisposable.add(
            mFavoriteRepository.deleteFavorite(id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    Log.d("desfavorite", "completed")
                }, { t ->
                    Throwable(t)
                })
        )
    }

    fun isFavorite(id: Int) {
        composeDisposable.add(
            mFavoriteRepository.isFavorite2(id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    mIsFav.postValue(it)
                }, { t ->
                    Throwable(t)
                })
        )
    }
}