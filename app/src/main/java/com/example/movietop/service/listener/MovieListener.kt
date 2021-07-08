package com.example.movietop.service.listener

interface MovieListener {

    /**
     * Click para abrir os detalhes
     */
    fun onListClick(id: Int)

    /**
     * Favorita o filme
     */
    fun onFavoriteClick(id: Int)

    /**
     * Desfavorita o filme
     */
    fun onDesfavoriteClick(id: Int)

}