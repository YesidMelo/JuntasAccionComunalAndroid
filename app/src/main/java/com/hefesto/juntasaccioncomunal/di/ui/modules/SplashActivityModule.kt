package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.logica.componentes.splash.ui.SplashUI
import com.hefesto.juntasaccioncomunal.ui.activities.splash.SplashActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    fun provideSplashActivityViewModel(splashUI: SplashUI) = SplashActivityViewModel(splashUI= splashUI)
}