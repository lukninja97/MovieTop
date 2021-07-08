package com.example.movietop.service.repository.remote

import com.example.movietop.service.model.MovieModel
import com.example.movietop.service.model.MovieResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/popular?api_key=46dd1124d6df117ce9e6e1e0fae059f6")
    fun getMovies(): Observable<MovieResult>

    @GET("movie/{id}?api_key=46dd1124d6df117ce9e6e1e0fae059f6")
    fun getMovie(@Path(value = "id", encoded = true) id: Int): Observable<MovieModel>
}