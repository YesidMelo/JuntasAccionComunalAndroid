package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.helpers.helpersActivity.HelperActivities
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.NavegacionAplicacion
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.NavegacionAplicacionImpl
import com.hefesto.juntasaccioncomunal.interfaceUsuario.navegacion.helpers.helpersFragments.HelperFragment
import dagger.Module
import dagger.Provides

@Module
class NavegacionModule {

    //region primer nivel
    @Provides
    fun providesHelperActivities() = HelperActivities()

    @Provides
    fun providesHelperFragment() = HelperFragment()
    //endregion

    //region segundo nivel

    @Provides
    fun providesNavegacionTodaAplicacion(
        helperActivities: HelperActivities,
        helperFragment: HelperFragment
    ) : NavegacionAplicacion = NavegacionAplicacionImpl(
        helperActivities = helperActivities,
        helperFragment = helperFragment
    )

    //endregion

}