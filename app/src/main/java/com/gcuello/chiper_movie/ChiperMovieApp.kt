package com.gcuello.chiper_movie

import android.app.Application
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.drawee.backends.pipeline.Fresco
import com.gcuello.chiper_movie.R
import com.gcuello.chiper_movie.core.Common
import com.gcuello.chiper_movie.data.db.ConfigDatabase
import com.gcuello.chiper_movie.presentation.di.ApplicationComponent
import com.gcuello.chiper_movie.presentation.di.DaggerApplicationComponent
import com.gcuello.chiper_movie.presentation.di.ViewModelFactoryModule

class ChiperMovieApp : Application() {
    private var _component: ApplicationComponent? = null

    val movieComponent get() = _component!!

    override fun onCreate() {
        super.onCreate()
        _component = DaggerApplicationComponent
            .builder()
            .viewModelFactoryModule(ViewModelFactoryModule(ConfigDatabase.getInstance(this)))
            .build()
        Common.attachUrls(getString(R.string.base_url), getString(R.string.api_key))
        Fresco.initialize(this)
    }

}