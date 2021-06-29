package com.example.movietop.service.repository.remote

import com.example.movietop.service.model.MovieResult
import retrofit2.http.GET
import rx.Observable

interface MovieService {
    @GET("movie/popular?api_key=46dd1124d6df117ce9e6e1e0fae059f6")
    fun getMovies(): Observable<MovieResult>
}