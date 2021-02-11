package com.gcuello.chiper_movie.presentation.di

import com.gcuello.chiper_movie.presentation.ui.home.dashboard.DashboardFragment
import com.gcuello.chiper_movie.presentation.ui.splash.SplashActivity
import dagger.Component

@Component(modules = [ViewModelFactoryModule::class])
interface ApplicationComponent {
    fun inject(inject: SplashActivity)
    fun inject(inject: DashboardFragment)
}