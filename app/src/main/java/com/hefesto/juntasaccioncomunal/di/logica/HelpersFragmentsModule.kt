package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva.helpers.HelperSpinnerEstadosAfiliacionConfiguracionDirectivas
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva.helpers.HelperSpinnerRolesAfiliacionConfiguracionDirectiva
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers.HelperDetalleAfiliadoViewPagerNavegacion
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers.HelperSpinnerComitesEnJacHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers.HelperSpinnerEstadosAfiliadoHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers.HelperSpinnerTiposDocumentoRegistroAfiliadoHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.helpers.HelperSpinnerTiposTelefonoRegistroAfiliadoHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas.helpers.HelperRecyclerListaAfiliadosModificacionDirectiva
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.helpers.HelperFuncionalidadesReciclerview
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado.helper.HelperRecyclerViewListaAfiliadosRegistrarActualizar
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.helpers.HelperSpinnerTiposReunion
import dagger.Module
import dagger.Provides

@Module
class HelpersFragmentsModule {

    //region home

    @Provides
    fun providesHelperDetalleAfiliadoViewPagerNavegacion(): HelperDetalleAfiliadoViewPagerNavegacion = HelperDetalleAfiliadoViewPagerNavegacion()

    @Provides
    fun providesHelperFuncionalidadesReciclerview(): HelperFuncionalidadesReciclerview = HelperFuncionalidadesReciclerview()

    @Provides
    fun providesHelperRecyclerListaAfiliadosModificacionDirectiva() : HelperRecyclerListaAfiliadosModificacionDirectiva = HelperRecyclerListaAfiliadosModificacionDirectiva()

    @Provides
    fun providesHelperRecyclerViewListaAfiliadosRegistrarActualizar() = HelperRecyclerViewListaAfiliadosRegistrarActualizar()

    @Provides
    fun providesHelperSpinnerComitesEnJacHome() = HelperSpinnerComitesEnJacHome()

    @Provides
    fun providesHelperSpinnerEstadosAfiliadoHome() = HelperSpinnerEstadosAfiliadoHome()

    @Provides
    fun providesHelperSpinnerEstadosAfiliacionConfiguracionDirectivas(): HelperSpinnerEstadosAfiliacionConfiguracionDirectivas = HelperSpinnerEstadosAfiliacionConfiguracionDirectivas()

    @Provides
    fun providesHelperSpinnerRolesAfiliacionConfiguracionDirectiva(): HelperSpinnerRolesAfiliacionConfiguracionDirectiva = HelperSpinnerRolesAfiliacionConfiguracionDirectiva()

    @Provides
    fun providesHelperSpinnerTiposDocumentoRegistroAfiliadoHome(): HelperSpinnerTiposDocumentoRegistroAfiliadoHome = HelperSpinnerTiposDocumentoRegistroAfiliadoHome()

    @Provides
    fun providesHelperSpinnerTiposReunion(): HelperSpinnerTiposReunion = HelperSpinnerTiposReunion()

    @Provides
    fun providesHelperSpinnerTiposTelefonoRegistroAfiliadoHome(): HelperSpinnerTiposTelefonoRegistroAfiliadoHome = HelperSpinnerTiposTelefonoRegistroAfiliadoHome()

    //endregion
}