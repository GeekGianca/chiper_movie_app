package com.gcuello.chiper_movie.core

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private const val IS_REFRESH_GENRES = "genresRefresh"

    private const val APP_SETTINGS_KEY = "movie_app"
    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(APP_SETTINGS_KEY, Context.MODE_PRIVATE)
    }


    fun isRefresh(context: Context): Boolean {
        return getSharedPreferences(context).getBoolean(IS_REFRESH_GENRES, false)
    }

    fun isRefresh(context: Context, value: Boolean) {
        val editor = getSharedPreferences(context).edit()
        editor.putBoolean(IS_REFRESH_GENRES, value)
        editor.apply()
    }
}