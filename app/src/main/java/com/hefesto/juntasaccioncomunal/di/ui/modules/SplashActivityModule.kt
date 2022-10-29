package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.logica.componentes.splash.ui.SplashUI
import com.hefesto.juntasaccioncomunal.interfaceUsuario.activities.splash.SplashActivityViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.gestorPermisos.GestorPermisos
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    //region permisos
    @Provides
    fun providesGestorPermisos() : GestorPermisos = GestorPermisos()

    //endregion

    @Provides
    fun provideSplashActivityViewModel(splashUI: SplashUI) = SplashActivityViewModel(splashUI= splashUI)
}