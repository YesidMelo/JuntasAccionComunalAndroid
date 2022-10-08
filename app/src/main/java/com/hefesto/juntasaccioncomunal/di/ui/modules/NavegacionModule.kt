package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.NavegacionAplicacion
import dagger.Module
import dagger.Provides

@Module
class NavegacionModule {

    @Provides
    fun provideNavegacionAplicacion() = NavegacionAplicacion()
}