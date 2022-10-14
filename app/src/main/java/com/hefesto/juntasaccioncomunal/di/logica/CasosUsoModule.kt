package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.MiAplicacion
import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseRepositorio
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva.ActualizarAfiliadoEnDirectivaCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva.ActualizarAfiliadoEnDirectivaCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva.TraerEstadosAfiliadoEnDirectivaCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva.TraerEstadosAfiliadoEnDirectivaCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva.TraerRolesAfiliadosEnDirectivaCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva.TraerRolesAfiliadosEnDirectivaCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.listaAfiliadosModificacionDirectivas.TraerListaAfiliadosModificacionDirectivasCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.listaAfiliadosModificacionDirectivas.TraerListaAfiliadosModificacionDirectivasCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.panelControl.TraerFuncionalidadesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.panelControl.TraerFuncionalidadesCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.CargarTiposDocumentoCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.CargarTiposDocumentoCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.RegistrarAfiliadoHomeCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.RegistrarAfiliadoHomeCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.TraerListaAfiliadosRegistroActualizacionCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado.TraerListaAfiliadosRegistroActualizacionCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeRepositorio
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.IniciarSesionCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.IniciarSesionCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.RegistrarAfiliadoCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.RegistrarAfiliadoCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.RegistrarJACCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.RegistrarJACCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.TraerListaJACsRegistradasCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.TraerListaJACsRegistradasCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.TraerTipoDocumentosCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.TraerTipoDocumentosCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.TraerTiposTelefonoCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.TraerTiposTelefonoCasoUsoImpl
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

    //region home

    @Provides
    fun providesActualizarAfiliadoEnDirectivaCasoUso(
        homeRepositorio: HomeRepositorio
    ): ActualizarAfiliadoEnDirectivaCasoUso = ActualizarAfiliadoEnDirectivaCasoUsoImpl(
        homeRepositorio = homeRepositorio
    )

    @Provides
    fun providesCargarTiposDocumentoCasoUso() : CargarTiposDocumentoCasoUso = CargarTiposDocumentoCasoUsoImpl(context = MiAplicacion.traerInstancia()!!.applicationContext)

    @Provides
    fun providesRegistrarAfiliadoHomeCasoUso(): RegistrarAfiliadoHomeCasoUso = RegistrarAfiliadoHomeCasoUsoImpl()

    @Provides
    fun providesTraerEstadosAfiliadoEnDirectivaCasoUso() : TraerEstadosAfiliadoEnDirectivaCasoUso
    = TraerEstadosAfiliadoEnDirectivaCasoUsoImpl(context = MiAplicacion.traerInstancia()!!.applicationContext)

    @Provides
    fun providesTraerFuncionalidadesCasoUso(
        homeRepositorio: HomeRepositorio
    ) : TraerFuncionalidadesCasoUso = TraerFuncionalidadesCasoUsoImpl(
        homeRepositorio = homeRepositorio
    )

    @Provides
    fun providesTraerListaAfiliadosModificacionDirectivasCasoUso(
        homeRepositorio: HomeRepositorio
    ) : TraerListaAfiliadosModificacionDirectivasCasoUso = TraerListaAfiliadosModificacionDirectivasCasoUsoImpl(
        homeRepositorio = homeRepositorio
    )

    @Provides
    fun providesTraerListaAfiliadosRegistroActualizacionCasoUso(
        homeRepositorio: HomeRepositorio
    ) : TraerListaAfiliadosRegistroActualizacionCasoUso = TraerListaAfiliadosRegistroActualizacionCasoUsoImpl(
        homeRepositorio = homeRepositorio
    )

    @Provides
    fun providesTraerRolesAfiliadosEnDirectivaCasoUso(): TraerRolesAfiliadosEnDirectivaCasoUso
    = TraerRolesAfiliadosEnDirectivaCasoUsoImpl(context = MiAplicacion.traerInstancia()!!.applicationContext)


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