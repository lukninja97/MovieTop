package com.example.movietop.service.model

import com.google.gson.annotations.SerializedName

data class MovieModel (

    @SerializedName("title")
    val title : String = "",

    @SerializedName("overview")
    val resumo : String = "",

    @SerializedName("poster_path")
    val poster : String = "",

    val favorite : Boolean = false
    )