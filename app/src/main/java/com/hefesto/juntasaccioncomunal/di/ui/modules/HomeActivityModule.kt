package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.interfaceUsuario.activities.home.HomeActivityViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva.ConfiguracionAfiliadoEnDirectivaViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas.ListaAfiliadosModificacionDirectivasFragmentViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.PanelControlFragmentViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado.RegistrarAfiliadoHomeViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.ConfiguracionAfiliadoEnDirectivaUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.HomeActivityUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.ListaAfiliadosActualizacionDirectivaUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.PanelControlFragmenUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.RegistroActualizacionAfiliadoUI
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    fun providesHomeActivityView(
        homeActivityUI : HomeActivityUI
    ): HomeActivityViewModel = HomeActivityViewModel(
        homeActivityUI = homeActivityUI
    )

    @Provides
    fun providesPanelControlFragmentViewModel(
        panelControlFragmenUI: PanelControlFragmenUI
    ) : PanelControlFragmentViewModel = PanelControlFragmentViewModel(
        panelControlFragmenUI = panelControlFragmenUI
    )

    @Provides
    fun providesListaAfiliadosModificacionDirectivasFragmentViewModel(
        listaAfiliadosActualizacionDirectivaUI: ListaAfiliadosActualizacionDirectivaUI
    ) : ListaAfiliadosModificacionDirectivasFragmentViewModel = ListaAfiliadosModificacionDirectivasFragmentViewModel(
        listaAfiliadosActualizacionDirectivaUI = listaAfiliadosActualizacionDirectivaUI
    )

    @Provides
    fun providesConfiguracionAfiliadoEnDirectivaViewModel(
        configuracionAfiliadoEnDirectivaUI: ConfiguracionAfiliadoEnDirectivaUI,
        cargarEscuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso
    ) :ConfiguracionAfiliadoEnDirectivaViewModel = ConfiguracionAfiliadoEnDirectivaViewModel(
        configuracionAfiliadoEnDirectivaUI = configuracionAfiliadoEnDirectivaUI,
        cargarEscuchadorExcepcionesCasoUso = cargarEscuchadorExcepcionesCasoUso
    )

    @Provides
    fun providesRegistrarAfiliadoHomeViewModel(
        registroActualizacionAfiliadoUI: RegistroActualizacionAfiliadoUI
    ) : RegistrarAfiliadoHomeViewModel = RegistrarAfiliadoHomeViewModel(
        registroActualizacionAfiliadoUI = registroActualizacionAfiliadoUI
    )

}