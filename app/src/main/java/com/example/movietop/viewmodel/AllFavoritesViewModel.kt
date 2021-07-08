package com.example.movietop.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movietop.service.model.FavoriteModel
import com.example.movietop.service.repository.FavoriteRepository
import io.reactivex.schedulers.Schedulers

class AllFavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val mFavoriteRepository = FavoriteRepository(application)

    private val mFavoriteList = MutableLiveData<List<FavoriteModel>>()
    val favoriteList: LiveData<List<FavoriteModel>> = mFavoriteList

    @SuppressLint("CheckResult")
    fun load(){
        mFavoriteRepository.loadFavorites()
            .subscribeOn(Schedulers.io())
            .subscribe({
                mFavoriteList.postValue(it)
            },{
                e -> e.printStackTrace()
            },{

            })
    }
}