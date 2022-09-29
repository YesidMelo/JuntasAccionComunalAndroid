package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.ui.navegacion.NavegacionAplicacion
import dagger.Module
import dagger.Provides

@Module
class NavegacionModule {

    @Provides
    fun provideNavegacionAplicacion() = NavegacionAplicacion()
}