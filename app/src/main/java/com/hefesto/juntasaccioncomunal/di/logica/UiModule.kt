package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.IniciarSesionCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.IniciarSesionFragmentUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.LoginActivityUI
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.casosUso.PrecargaAplicacionFinalizadaCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.ui.SplashUI
import dagger.Module
import dagger.Provides

@Module
class UiModule {

    //region login
    @Provides
    fun providesLoginActivityUI() = LoginActivityUI()

    @Provides
    fun providesIniciarSesionFragmentUI(iniciarSesionCasoUso: IniciarSesionCasoUso) = IniciarSesionFragmentUI(iniciarSesionCasoUso = iniciarSesionCasoUso)
    //endregion

    //region splash
    @Provides
    fun providesSplashUI(precargaAplicacionFinalizadaCasoUso: PrecargaAplicacionFinalizadaCasoUso)
        = SplashUI(
            precargaAplicacionFinalizadaCasoUso = precargaAplicacionFinalizadaCasoUso
        )

    //endregion
}