package com.example.movietop.service.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movie")
data class MovieModel(

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("title")
    val title: String = "",

    @SerializedName("overview")
    val resumo: String = "",

    @SerializedName("poster_path")
    val poster: String = "",

    @SerializedName("backdrop_path")
    val fundo: String = "",

    @SerializedName("release_date")
    val data: String = "",

    @SerializedName("vote_average")
    val nota: String = "",

    @SerializedName("vote_count")
    val votos: String = ""
): Serializable{
    fun insert(): FavoriteModel {
        return FavoriteModel(
            id = this.id,
            title = this.title,
            resumo = this.resumo,
            poster = this.poster,
            fundo = this.fundo,
            data = this.data,
            nota = this.nota,
            votos = this.votos
        )
    }
}

