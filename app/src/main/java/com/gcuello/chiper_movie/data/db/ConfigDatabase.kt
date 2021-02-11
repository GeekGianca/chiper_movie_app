package com.gcuello.chiper_movie.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gcuello.chiper_movie.R
import com.gcuello.chiper_movie.data.db.converters.BitmapConverter
import com.gcuello.chiper_movie.data.db.dao.*
import com.gcuello.chiper_movie.data.db.entities.*

@Database(
    entities = [Genre::class, Movie::class, Cast::class, Crew::class, MovieDetail::class, Credit::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(BitmapConverter::class)
abstract class ConfigDatabase : RoomDatabase() {

    abstract fun genreDao(): GenreDao
    abstract fun popularMovieDao(): MovieDao
    abstract fun castDao(): CastDao
    abstract fun crewDao(): CrewDao
    abstract fun movieDetailDao(): MovieDetailDao
    abstract fun creditDao(): CreditDao

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