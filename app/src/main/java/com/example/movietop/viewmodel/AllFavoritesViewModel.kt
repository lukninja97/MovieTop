package com.example.movietop.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.movietop.service.model.FavoriteModel
import com.example.movietop.service.repository.FavoriteRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AllFavoritesViewModel(application: Application) : AndroidViewModel(application),
    LifecycleObserver {

    private val mFavoriteRepository = FavoriteRepository(application)

    private val mFavoriteList = MutableLiveData<List<FavoriteModel>>()
    val favoriteList: LiveData<List<FavoriteModel>> = mFavoriteList

    private val compositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun load() {
        compositeDisposable.add(
            mFavoriteRepository.loadFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    mFavoriteList.postValue(it)
                }, { e ->
                    e.printStackTrace()
                }, {

                })
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun fullClear() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        fullClear()
    }
}