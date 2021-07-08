package com.example.movietop.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movietop.service.model.FavoriteModel

@Database(entities = [FavoriteModel::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun favoriteDAO(): FavoriteDAO

    companion object {
        @Volatile
        private lateinit var INSTANCE: MovieDataBase

        fun getDatabase(context: Context): MovieDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context, MovieDataBase::class.java, "movie_database")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}