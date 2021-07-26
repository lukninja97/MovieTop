package com.example.movietop.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.movietop.service.model.MovieModel
import com.example.movietop.service.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AllMoviesViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {

    private val mMovieRepository = MovieRepository(application)

    private val mMovieList = MutableLiveData<List<MovieModel>>()
    val movieList: LiveData<List<MovieModel>> = mMovieList

    private val compositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun load() {
        compositeDisposable.add(
            mMovieRepository.loadMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    mMovieList.postValue(it.results)
                }, { e ->
                    e.printStackTrace()
                }, {

                })
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun fullClear(){
        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        fullClear()
    }
}