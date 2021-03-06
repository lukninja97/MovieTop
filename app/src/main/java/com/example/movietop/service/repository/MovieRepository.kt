package com.example.movietop.service.repository

import android.content.Context
import com.example.movietop.service.model.MovieModel
import com.example.movietop.service.model.MovieResult
import com.example.movietop.service.repository.remote.MovieService
import com.example.movietop.service.repository.remote.RetrofitClient
import io.reactivex.Observable

class MovieRepository(context: Context){

    private val mRemote = RetrofitClient.createService(MovieService::class.java)

    fun loadMovies(): Observable<MovieResult> {
        return mRemote.getMovies()
    }

    fun getMovie(id: Int): Observable<MovieModel> {
        return mRemote.getMovie(id)
    }
}