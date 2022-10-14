package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.helpers.helpersActivity.HelperActivities
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.NavegacionAplicacion
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.NavegacionAplicacionImpl
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.helpers.helpersFragments.HelperFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.helpers.helpersFragments.HelperNavegacionFragmentConBeginTransaction
import dagger.Module
import dagger.Provides

@Module
class NavegacionModule {

    //region primer nivel
    @Provides
    fun providesHelperActivities() = HelperActivities()

    @Provides
    fun providesHelperFragment() = HelperFragment()

    @Provides
    fun providesHelperNavegacionFragmentConBeginTransaction() = HelperNavegacionFragmentConBeginTransaction()
    //endregion

    //region segundo nivel

    @Provides
    fun providesNavegacionTodaAplicacion(
        helperActivities: HelperActivities,
        helperNavegacionFragmentConBeginTransaction: HelperNavegacionFragmentConBeginTransaction
    ) : NavegacionAplicacion = NavegacionAplicacionImpl(
        helperActivities = helperActivities,
        helperNavegacionFragmentConBeginTransaction = helperNavegacionFragmentConBeginTransaction
    )

    //endregion

}