package com.example.movietop.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movietop.service.model.FavoriteModel
import com.example.movietop.service.repository.FavoriteRepository
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val mFavoriteRepository = FavoriteRepository(application)

    private val mFavorite = MutableLiveData<FavoriteModel>()
    var favorite: LiveData<FavoriteModel> = mFavorite

    @SuppressLint("CheckResult")
    fun load(id: Int){
        mFavoriteRepository.getFavorite(id)
            .subscribeOn(Schedulers.io())
            .subscribe({
                mFavorite.postValue(it)
            },{
                e -> e.printStackTrace()
            },{

            })

    }
}