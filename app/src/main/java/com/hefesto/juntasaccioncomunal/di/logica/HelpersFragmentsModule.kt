package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva.helpers.HelperSpinnerEstadosAfiliacionConfiguracionDirectivas
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva.helpers.HelperSpinnerRolesAfiliacionConfiguracionDirectiva
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas.helpers.HelperRecyclerListaAfiliadosModificacionDirectiva
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.helpers.HelperFuncionalidadesReciclerview
import dagger.Module
import dagger.Provides

@Module
class HelpersFragmentsModule {

    //region home
    @Provides
    fun providesHelperFuncionalidadesReciclerview(): HelperFuncionalidadesReciclerview = HelperFuncionalidadesReciclerview()

    @Provides
    fun providesHelperRecyclerListaAfiliadosModificacionDirectiva() : HelperRecyclerListaAfiliadosModificacionDirectiva = HelperRecyclerListaAfiliadosModificacionDirectiva()

    @Provides
    fun providesHelperSpinnerEstadosAfiliacionConfiguracionDirectivas(): HelperSpinnerEstadosAfiliacionConfiguracionDirectivas = HelperSpinnerEstadosAfiliacionConfiguracionDirectivas()

    @Provides
    fun providesHelperSpinnerRolesAfiliacionConfiguracionDirectiva(): HelperSpinnerRolesAfiliacionConfiguracionDirectiva = HelperSpinnerRolesAfiliacionConfiguracionDirectiva()

    //endregion
}