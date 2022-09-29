package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.ui.activities.splash.SplashActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    fun provideSplashActivityViewModel(memoriaCache: MemoriaCache) = SplashActivityViewModel(cache = memoriaCache)
}