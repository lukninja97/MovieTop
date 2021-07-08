package com.example.movietop.service.repository.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object{

        private lateinit var retrofit: Retrofit

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()

            val baseUrl = "https://api.themoviedb.org/3/"

            if (!::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}