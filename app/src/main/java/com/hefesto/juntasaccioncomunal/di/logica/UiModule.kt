package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.MiAplicacion
import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva.ActualizarAfiliadoEnDirectivaCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva.TraerEstadosAfiliadoEnDirectivaCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva.TraerRolesAfiliadosEnDirectivaCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.listaAfiliadosModificacionDirectivas.TraerListaAfiliadosModificacionDirectivasCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.panelControl.TraerFuncionalidadesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.ConfiguracionAfiliadoEnDirectivaUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.HomeActivityUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.ListaAfiliadosModificacionDirectivasFragmentUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.PanelControlFragmenUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.IniciarSesionCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.RegistrarAfiliadoCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.RegistrarJACCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.TraerListaJACsRegistradasCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.TraerTipoDocumentosCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.TraerTiposTelefonoCasoUso
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

    //region home
    @Provides
    fun providesHomeActivityUI(escuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso) = HomeActivityUI(escuchadorExcepciones = escuchadorExcepcionesCasoUso)

    @Provides
    fun providesPanelControlFragmenUI(
        escuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso,
        traerFuncionalidadesCasoUso: TraerFuncionalidadesCasoUso
    ) : PanelControlFragmenUI = PanelControlFragmenUI(
        escuchadorExcepciones = escuchadorExcepcionesCasoUso,
        traerFuncionalidadesCasoUso = traerFuncionalidadesCasoUso
    )

    @Provides
    fun providesListaAfiliadosModificacionDirectivasFragmentUI(
        cargarEscuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso,
        traerListaAfiliadosModificacionDirectivasCasoUso: TraerListaAfiliadosModificacionDirectivasCasoUso
    ) : ListaAfiliadosModificacionDirectivasFragmentUI = ListaAfiliadosModificacionDirectivasFragmentUI(
        escuchadorExcepciones = cargarEscuchadorExcepcionesCasoUso,
        traerListaAfiliadosModificacionDirectivasCasoUso = traerListaAfiliadosModificacionDirectivasCasoUso
    )

    @Provides
    fun providesConfiguracionAfiliadoEnDirectivaUI(
        cargarEscuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso,
        actualizarAfiliadoEnDirectivaCasoUso: ActualizarAfiliadoEnDirectivaCasoUso,
        traerEstadosAfiliadoEnDirectivaCasoUso: TraerEstadosAfiliadoEnDirectivaCasoUso,
        traerRolesAfiliadosEnDirectivaCasoUso: TraerRolesAfiliadosEnDirectivaCasoUso,
    ) : ConfiguracionAfiliadoEnDirectivaUI = ConfiguracionAfiliadoEnDirectivaUI(
        actualizarAfiliadoEnDirectivaCasoUso = actualizarAfiliadoEnDirectivaCasoUso,
        escuchadorExcepciones = cargarEscuchadorExcepcionesCasoUso,
        traerEstadosAfiliadoEnDirectivaCasoUso = traerEstadosAfiliadoEnDirectivaCasoUso,
        traerRolesAfiliadosEnDirectivaCasoUso = traerRolesAfiliadosEnDirectivaCasoUso,
    )

    //endregion

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
        traerListaJACsRegistradasCasoUso: TraerListaJACsRegistradasCasoUso,
        traerTipoDocumentosCasoUso: TraerTipoDocumentosCasoUso,
        traerTiposTelefonoCasoUso: TraerTiposTelefonoCasoUso
    ) = RegistrarAfiliadoFragmentUI(
        escuchadorExcepciones = escuchadorExcepcionesCasoUso,
        registrarAfiliadoCasoUso = registrarAfiliadoCasoUso,
        traerListaJACsRegistradasCasoUso = traerListaJACsRegistradasCasoUso,
        traerTipoDocumentosCasoUso= traerTipoDocumentosCasoUso,
        traerTiposTelefonoCasoUso= traerTiposTelefonoCasoUso
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