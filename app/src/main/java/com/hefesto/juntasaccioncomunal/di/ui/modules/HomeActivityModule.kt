package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.interfaceUsuario.activities.home.HomeActivityViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva.ConfiguracionAfiliadoEnDirectivaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.DetalleAfiliadoRegistroActualizacionViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.contactoAfiliado.ContactoAfiliadoRegistroActualizacionViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.datosBasicosAfiliado.DatosRegistroActualizacionViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.detalleEnJACAfiliado.DetalleEnJacViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.seguridadAfiliado.SeguridadAfiliadoViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas.ListaAfiliadosModificacionDirectivasFragmentViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.PanelControlFragmentViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado.RegistrarAfiliadoHomeViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion.AgendarReunionViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa.CrearActaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf.GenerarActaPdfViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaReunionesCreacionActa.ListaReunionesCreacionActaViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.ConfiguracionAfiliadoEnDirectivaUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.HomeActivityUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.ListaAfiliadosActualizacionDirectivaUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.PanelControlFragmenUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion.AgendarReunionUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion.CrearActaUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion.GenerarActaPDFUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion.ListaReunionesCreacionActaUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.ContactoAfiliadoUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.DatosBasicosAfiliadoUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.DetalleAfiliadoEnJacUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.DetalleAfiliadoRegistroActualizacionUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.RegistroActualizacionAfiliadoUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.SeguridadAfiliadoUI
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    //region afiliados
    @Provides
    fun providesConfiguracionAfiliadoEnDirectivaViewModel(
        configuracionAfiliadoEnDirectivaUI: ConfiguracionAfiliadoEnDirectivaUI,
        cargarEscuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso
    ) :ConfiguracionAfiliadoEnDirectivaViewModel = ConfiguracionAfiliadoEnDirectivaViewModel(
        configuracionAfiliadoEnDirectivaUI = configuracionAfiliadoEnDirectivaUI,
        cargarEscuchadorExcepcionesCasoUso = cargarEscuchadorExcepcionesCasoUso
    )

    @Provides
    fun providesContactoAfiliadoRegistroActualuzacionViewModel(
        contactoAfiliadoUI: ContactoAfiliadoUI
    ) : ContactoAfiliadoRegistroActualizacionViewModel = ContactoAfiliadoRegistroActualizacionViewModel(
        contactoAfiliadoUI = contactoAfiliadoUI
    )

    @Provides
    fun providesDatosBasicosAfiliadoViewModel(
        dataBasicosAfiliadoUI: DatosBasicosAfiliadoUI
    ) : DatosRegistroActualizacionViewModel = DatosRegistroActualizacionViewModel(
        datosBasicosAfiliadoUI = dataBasicosAfiliadoUI
    )

    @Provides
    fun providesDetalleAfiliadoEnJacViewModel(
        detalleAfiliadoEnJacUI: DetalleAfiliadoEnJacUI
    ) : DetalleEnJacViewModel = DetalleEnJacViewModel(
        detalleAfiliadoEnJacUI = detalleAfiliadoEnJacUI
    )

    @Provides
    fun providesDetalleAfiliadoRegistroActualizacionViewModel(
        detalleAfiliadoRegistroActualizacionUI: DetalleAfiliadoRegistroActualizacionUI
    ) : DetalleAfiliadoRegistroActualizacionViewModel = DetalleAfiliadoRegistroActualizacionViewModel(
        detalleAfiliadoRegistroActualizacionUI = detalleAfiliadoRegistroActualizacionUI
    )

    @Provides
    fun providesListaAfiliadosModificacionDirectivasFragmentViewModel(
        listaAfiliadosActualizacionDirectivaUI: ListaAfiliadosActualizacionDirectivaUI
    ) : ListaAfiliadosModificacionDirectivasFragmentViewModel = ListaAfiliadosModificacionDirectivasFragmentViewModel(
        listaAfiliadosActualizacionDirectivaUI = listaAfiliadosActualizacionDirectivaUI
    )

    @Provides
    fun providesRegistrarAfiliadoHomeViewModel(
        registroActualizacionAfiliadoUI: RegistroActualizacionAfiliadoUI
    ) : RegistrarAfiliadoHomeViewModel = RegistrarAfiliadoHomeViewModel(
        registroActualizacionAfiliadoUI = registroActualizacionAfiliadoUI
    )

    @Provides
    fun providesSeguridadAfiliadoViewModel(
        seguridadAfiliadoUI: SeguridadAfiliadoUI
    ) : SeguridadAfiliadoViewModel = SeguridadAfiliadoViewModel(
        seguridadAfiliadoUI = seguridadAfiliadoUI
    )
    //endregion

    //region asambleas/reuniones
    @Provides
    fun providesAgendarReunionAsambleaViewModel(
        agendarReunionUI: AgendarReunionUI
    ) : AgendarReunionViewModel = AgendarReunionViewModel(
        agendarReunionUI = agendarReunionUI
    )

    @Provides
    fun providesCrearActaReunionViewModel(
        listaReunionesCreacionActaUI: ListaReunionesCreacionActaUI
    ) : ListaReunionesCreacionActaViewModel = ListaReunionesCreacionActaViewModel(
        listaReunionesCreacionActaUI = listaReunionesCreacionActaUI
    )

    @Provides
    fun providesCrearActaViewModel(
        crearActaUI : CrearActaUI
    ) : CrearActaViewModel = CrearActaViewModel(
        crearActaUI = crearActaUI
    )

    @Provides
    fun providesGenerarActaPdfViewModel(
        generarActaPDFUI: GenerarActaPDFUI
    ) = GenerarActaPdfViewModel(
        generarActaPDFUI = generarActaPDFUI
    )

    //endregion

    @Provides
    fun providesPanelControlFragmentViewModel(
        panelControlFragmenUI: PanelControlFragmenUI
    ) : PanelControlFragmentViewModel = PanelControlFragmentViewModel(
        panelControlFragmenUI = panelControlFragmenUI
    )

    @Provides
    fun providesHomeActivityView(
        homeActivityUI : HomeActivityUI
    ): HomeActivityViewModel = HomeActivityViewModel(
        homeActivityUI = homeActivityUI
    )

}