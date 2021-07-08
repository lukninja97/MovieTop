package com.example.movietop.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite")
data class FavoriteModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val title: String = "",
    val resumo: String = "",
    val poster: String = "",
    val fundo : String = "",
    val data : String = "",
    val nota : String = "",
    val votos : String = ""
    ): Serializable