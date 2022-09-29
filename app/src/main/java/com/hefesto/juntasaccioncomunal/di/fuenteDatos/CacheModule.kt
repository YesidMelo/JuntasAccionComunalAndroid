package com.hefesto.juntasaccioncomunal.di.fuenteDatos

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCacheImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    fun provideCache() : MemoriaCache = MemoriaCacheImpl.traerInstancia()
}