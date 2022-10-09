package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.interfaceUsuario.activities.home.HomeActivityViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.PanelControlFragmentViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.HomeActivityUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.PanelControlFragmenUI
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
}