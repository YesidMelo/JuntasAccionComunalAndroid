package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseRepositorio
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.IniciarSesionCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.IniciarSesionCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.LoginRepositorio
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.casosUso.PrecargaAplicacionFinalizadaCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.casosUso.PrecargaAplicacionFinalizadaCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.*
import dagger.Module
import dagger.Provides

@Module
class CasosUsoModule {

    //region base
    @Provides
    fun providesCargarEscuchadorExcepcionesCasoUso(
        baseRepositorio: BaseRepositorio
    ): CargarEscuchadorExcepcionesCasoUso = CargarEscuchadorExcepcionesCasoUsoImpl(baseRepositorio = baseRepositorio)
    //endregion

    //region login
    @Provides
    fun providesIniciarSesionCasoUso(
        loginRepositorio: LoginRepositorio
    ) : IniciarSesionCasoUso = IniciarSesionCasoUsoImpl(loginRepositorio = loginRepositorio)
    //endregion

    //region splash
    @Provides
    fun providesPrecargaAplicacionFinalizadaCasoUso(splashRepositorio: SplashRepositorio)
    : PrecargaAplicacionFinalizadaCasoUso = PrecargaAplicacionFinalizadaCasoUsoImpl(splashRepositorio = splashRepositorio)
    //endregion
}