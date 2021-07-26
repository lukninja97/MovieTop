package com.example.movietop.service.repository

import android.content.Context
import com.example.movietop.service.model.FavoriteModel
import com.example.movietop.service.repository.local.MovieDataBase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class FavoriteRepository(context: Context) {

    private val mFavoriteDataBase = MovieDataBase.getDatabase(context).favoriteDAO()

    fun loadFavorites(): Observable<List<FavoriteModel>> {
        return mFavoriteDataBase.getAll()
    }

    fun getFavorite(id: Int): Observable<FavoriteModel> {
        return mFavoriteDataBase.getFavorite(id)
    }

    fun insertFavorite(favorite: FavoriteModel): Completable {
        return mFavoriteDataBase.insert(favorite)
    }
    fun deleteFavorite(int: Int): Completable {
        return mFavoriteDataBase.delete(int)
    }

    /*fun isFavorite(id: Int): Boolean{
        //return mFavoriteDataBase.isFavorite(id)
    }*/

    fun isFavorite2(id: Int): Single<Boolean>{
        return mFavoriteDataBase.isFavorite(id)
    }
}