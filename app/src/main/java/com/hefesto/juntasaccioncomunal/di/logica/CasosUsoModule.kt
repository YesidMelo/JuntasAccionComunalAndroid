package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.MiAplicacion
import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseRepositorio
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.*
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

    @Provides
    fun providesRegistrarJACCasoUso(
        loginRepositorio: LoginRepositorio
    ) : RegistrarJACCasoUso = RegistrarJACCasoUsoImpl(loginRepositorio = loginRepositorio)

    @Provides
    fun providesRegistrarAfiliadoCasoUso(
        loginRepositorio: LoginRepositorio
    ) : RegistrarAfiliadoCasoUso = RegistrarAfiliadoCasoUsoImpl(
        loginRepositorio = loginRepositorio
    )

    @Provides
    fun providesTraerListaJACsRegistradasCasoUso(
        loginRepositorio: LoginRepositorio
    ) : TraerListaJACsRegistradasCasoUso = TraerListaJACsRegistradasCasoUsoImpl(
        loginRepositorio = loginRepositorio
    )

    @Provides
    fun providesTraerTipoDocumentosCasoUso() : TraerTipoDocumentosCasoUso
    = TraerTipoDocumentosCasoUsoImpl(context = MiAplicacion.traerInstancia()!!.applicationContext)

    @Provides
    fun providesTraerTiposTelefonoCasoUso(): TraerTiposTelefonoCasoUso
    = TraerTiposTelefonoCasoUsoImpl(context = MiAplicacion.traerInstancia()!!.applicationContext)

    //endregion

    //region splash
    @Provides
    fun providesPrecargaAplicacionFinalizadaCasoUso(splashRepositorio: SplashRepositorio)
    : PrecargaAplicacionFinalizadaCasoUso = PrecargaAplicacionFinalizadaCasoUsoImpl(splashRepositorio = splashRepositorio)
    //endregion
}