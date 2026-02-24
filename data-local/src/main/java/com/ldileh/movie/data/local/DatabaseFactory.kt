package com.ldileh.movie.data.local

import android.content.Context
import androidx.room.Room

object DatabaseFactory {

    fun create(context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_db"
        ).build()
    }

    fun createTest(context: Context): MovieDatabase{
        return Room.inMemoryDatabaseBuilder(
            context,
            MovieDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }
}