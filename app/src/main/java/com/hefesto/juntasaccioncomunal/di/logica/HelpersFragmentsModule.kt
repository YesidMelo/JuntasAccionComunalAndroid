package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.configuracionAfiliadoEnDirectiva.helpers.HelperSpinnerEstadosAfiliacionConfiguracionDirectivas
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.configuracionAfiliadoEnDirectiva.helpers.HelperSpinnerRolesAfiliacionConfiguracionDirectiva
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.helpers.HelperDetalleAfiliadoViewPagerNavegacion
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.helpers.HelperSpinnerComitesEnJacHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.helpers.HelperSpinnerEstadosAfiliadoHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.helpers.HelperSpinnerTiposDocumentoRegistroAfiliadoHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.helpers.HelperSpinnerTiposTelefonoRegistroAfiliadoHome
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas.helpers.HelperRecyclerListaAfiliadosModificacionDirectiva
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.helpers.HelperFuncionalidadesReciclerview
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado.helper.HelperRecyclerViewListaAfiliadosRegistrarActualizar
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.helpers.HelperAutocompleteConvocantesAgendarReunion
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.helpers.HelperRecyclerViewAgendarReunionListaPuntos
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.helpers.HelperSpinnerTiposReunion
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.helper.HelperFiltroBusquedaAfiliadoActaAsistencia
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.helper.HelperViewPagerFormulariosCompletarActas
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.helper.HelperViewPagerPuntosCrearActa
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.HelperGeneradorHtmlActaPDF
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.generadorPDF.HelperGeneradorPDFActa
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.helpers.HelperRecyclerListaActasReunionesParaPDF
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarConvocatoriaReunionPdf.HelperConfigurarConvocatoriaHtml
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarConvocatoriaReunionPdf.HelperGenerarConvocatoriaPDF
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaConvocatoriasReuniones.helpers.HelperRecyclerListaReunionesParaConvocatoria
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaReunionesCreacionActa.helpers.HelperRecyclerViewListaReunionesCrearActa
import dagger.Module
import dagger.Provides

@Module
class HelpersFragmentsModule {

    //region home

    //region afiliado
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
    fun providesHelperSpinnerTiposTelefonoRegistroAfiliadoHome(): HelperSpinnerTiposTelefonoRegistroAfiliadoHome = HelperSpinnerTiposTelefonoRegistroAfiliadoHome()
    //endregion

    //region reunion/asamblea

    @Provides
    fun providesHelperRecyclerViewAgendarReunionListaPuntos() : HelperRecyclerViewAgendarReunionListaPuntos = HelperRecyclerViewAgendarReunionListaPuntos()

    @Provides
    fun providesHelperSpinnerTiposReunion(): HelperSpinnerTiposReunion = HelperSpinnerTiposReunion()

    @Provides
    fun providesHelperRecyclerViewListaReunionesCrearActa() : HelperRecyclerViewListaReunionesCrearActa = HelperRecyclerViewListaReunionesCrearActa()

    @Provides
    fun providesHelperViewPagerFormulariosCompletarActas(): HelperViewPagerFormulariosCompletarActas = HelperViewPagerFormulariosCompletarActas()

    @Provides
    fun providesHelperViewPagerPuntosCrearActa() :  HelperViewPagerPuntosCrearActa = HelperViewPagerPuntosCrearActa()

    @Provides
    fun providesHelperFiltroBusquedaAfiliadoActaAsistencia(): HelperFiltroBusquedaAfiliadoActaAsistencia = HelperFiltroBusquedaAfiliadoActaAsistencia()

    @Provides
    fun providesHelperRecyclerListaActasReunionesParaPDF(): HelperRecyclerListaActasReunionesParaPDF = HelperRecyclerListaActasReunionesParaPDF()

    @Provides
    fun providesHelperGeneradorHtmlParaGenerarPDF(): HelperGeneradorHtmlActaPDF = HelperGeneradorHtmlActaPDF()

    @Provides
    fun providesHelperAutocompleteConvocantesAgendarReunion() = HelperAutocompleteConvocantesAgendarReunion()

    @Provides
    fun providesHelperRecyclerListaReunionesParaConvocatoria(): HelperRecyclerListaReunionesParaConvocatoria = HelperRecyclerListaReunionesParaConvocatoria()

    @Provides
    fun providesHelperConfigurarConvocatoriaHtml(): HelperConfigurarConvocatoriaHtml = HelperConfigurarConvocatoriaHtml()

    @Provides
    fun providesHelperGenerarConvocatoriaPDF(): HelperGenerarConvocatoriaPDF = HelperGenerarConvocatoriaPDF()

    @Provides
    fun providesHelperGeneradorPDFActa(): HelperGeneradorPDFActa = HelperGeneradorPDFActa()
    //endregion

    //endregion
}