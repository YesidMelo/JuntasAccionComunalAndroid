package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.helpers.HelperFuncionalidadesReciclerview
import dagger.Module
import dagger.Provides

@Module
class HelpersFragmentsModule {

    //region home
    @Provides
    fun providesHelperFuncionalidadesReciclerview(): HelperFuncionalidadesReciclerview = HelperFuncionalidadesReciclerview()
    //endregion
}