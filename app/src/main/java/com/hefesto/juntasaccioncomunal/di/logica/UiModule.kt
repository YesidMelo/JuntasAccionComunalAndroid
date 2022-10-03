package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.IniciarSesionCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.RegistrarAfiliadoCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.RegistrarJACCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.TraerListaJACsRegistradasCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.IniciarSesionFragmentUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.LoginActivityUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.RegistrarAfiliadoFragmentUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.RegistrarJACFragmentUI
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.casosUso.PrecargaAplicacionFinalizadaCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.ui.SplashUI
import dagger.Module
import dagger.Provides

@Module
class UiModule {

    //region login
    @Provides
    fun providesLoginActivityUI(cargarEscuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso) = LoginActivityUI(escuchadorExcepciones = cargarEscuchadorExcepcionesCasoUso)

    @Provides
    fun providesIniciarSesionFragmentUI(
        iniciarSesionCasoUso: IniciarSesionCasoUso,
        cargarEscuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso
    ) = IniciarSesionFragmentUI(iniciarSesionCasoUso = iniciarSesionCasoUso, escuchadorExcepciones = cargarEscuchadorExcepcionesCasoUso)

    @Provides
    fun providesRegistrarJACFragmentUI(
        registrarJACCasoUso: RegistrarJACCasoUso,
        escuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso
    ) = RegistrarJACFragmentUI(
        escuchadorExcepciones = escuchadorExcepcionesCasoUso,
        registrarJACCasoUso = registrarJACCasoUso
    )

    @Provides
    fun providesRegistroAfiliadoFragmentUI(
        escuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso,
        registrarAfiliadoCasoUso: RegistrarAfiliadoCasoUso,
        traerListaJACsRegistradasCasoUso: TraerListaJACsRegistradasCasoUso
    ) = RegistrarAfiliadoFragmentUI(
        escuchadorExcepciones = escuchadorExcepcionesCasoUso,
        registrarAfiliadoCasoUso = registrarAfiliadoCasoUso,
        traerListaJACsRegistradasCasoUso = traerListaJACsRegistradasCasoUso
    )

    //endregion

    //region splash
    @Provides
    fun providesSplashUI(
        precargaAplicacionFinalizadaCasoUso: PrecargaAplicacionFinalizadaCasoUso,
        cargarEscuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso
    ) = SplashUI(
        precargaAplicacionFinalizadaCasoUso = precargaAplicacionFinalizadaCasoUso,
        escuchadorExcepciones = cargarEscuchadorExcepcionesCasoUso
    )

    //endregion
}