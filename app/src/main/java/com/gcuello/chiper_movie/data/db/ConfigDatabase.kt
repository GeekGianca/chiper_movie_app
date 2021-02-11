package com.gcuello.chiper_movie.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gcuello.chiper_movie.R
import com.gcuello.chiper_movie.data.db.converters.BitmapConverter
import com.gcuello.chiper_movie.data.db.dao.GenreDao
import com.gcuello.chiper_movie.data.db.dao.MovieDao
import com.gcuello.chiper_movie.data.db.entities.Genre
import com.gcuello.chiper_movie.data.db.entities.Movie

@Database(entities = [Genre::class, Movie::class], version = 3, exportSchema = false)
@TypeConverters(BitmapConverter::class)
abstract class ConfigDatabase : RoomDatabase() {

    abstract fun genreDao(): GenreDao
    abstract fun popularMovieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: ConfigDatabase? = null

        fun getInstance(context: Context): ConfigDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ConfigDatabase::class.java,
                    context.resources.getString(R.string.database_name)
                ).fallbackToDestructiveMigration()
                    .build().also {
                        INSTANCE = it
                    }
            }
        }
    }
}