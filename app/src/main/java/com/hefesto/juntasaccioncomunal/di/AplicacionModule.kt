package com.hefesto.juntasaccioncomunal.di

import com.hefesto.juntasaccioncomunal.MiAplicacion
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AplicacionModule {

    @Singleton
    @Provides
    fun providesContext(aplicacion: MiAplicacion) = aplicacion
}