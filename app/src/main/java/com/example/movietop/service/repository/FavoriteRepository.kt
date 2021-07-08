package com.example.movietop.service.repository

import android.content.Context
import com.example.movietop.service.model.FavoriteModel
import com.example.movietop.service.repository.local.MovieDataBase
import io.reactivex.Observable

class FavoriteRepository(context: Context) {

    private val mFavoriteDataBase = MovieDataBase.getDatabase(context).favoriteDAO()

    fun loadFavorites(): Observable<List<FavoriteModel>> {
        return mFavoriteDataBase.getAll()
    }

    fun getFavorite(id: Int): Observable<FavoriteModel> {
        return mFavoriteDataBase.getFavorite(id)
    }

    fun insertFavorite(favorite: FavoriteModel) {
        mFavoriteDataBase.insert(favorite)
    }
    fun deleteFavorite(int: Int) {
        mFavoriteDataBase.delete(int)
    }

    fun isFavorite(id: Int): Boolean{
        return mFavoriteDataBase.isFavorite(id)
    }
}