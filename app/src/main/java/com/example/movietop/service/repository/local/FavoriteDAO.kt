package com.example.movietop.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movietop.service.model.FavoriteModel
import io.reactivex.Observable

@Dao
interface FavoriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: FavoriteModel)

    @Query("DELETE FROM favorite WHERE id = :id")
    fun delete(id: Int): Int

    @Query("SELECT * FROM favorite")
    fun getAll(): Observable<List<FavoriteModel>>

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun getFavorite(id: Int): Observable<FavoriteModel>

    @Query("SELECT EXISTS (SELECT 1 FROM favorite WHERE id = :id)")
    fun isFavorite(id: Int): Boolean
}