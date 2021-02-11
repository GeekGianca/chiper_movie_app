package com.gcuello.chiper_movie

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gcuello.chiper_movie.data.db.ConfigDatabase
import com.gcuello.chiper_movie.data.db.dao.MovieDao
import com.gcuello.chiper_movie.data.db.entities.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RoomMovieTest {

    private lateinit var movieDao: MovieDao
    private lateinit var db: ConfigDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, ConfigDatabase::class.java
        ).build()
        movieDao = db.popularMovieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val movie = Movie(
            85271,
            "Popolvar, Biggest in the World",
            "1982-12-24",
            "popular",
            null,
            "sk",
            "2312",
            "Popolvár najväčší na svete",
            "A fairy-tale about knight honour, punished evil and tender love. Three royal brothers are put to the test in the fight with the king of the underworld in order to save captured princess, Láskykvet.",
            1.67,
            "/jCCdvtvl1TwMtKie7M9Bb89CTZP.jpg",
            false,
            7.0,
            1,
            false
        )
        movieDao.insert(movie)
        val byName = movieDao.selectByName(movie.title!!)
        assertThat(byName.title, equalTo(movie.title))
    }
}