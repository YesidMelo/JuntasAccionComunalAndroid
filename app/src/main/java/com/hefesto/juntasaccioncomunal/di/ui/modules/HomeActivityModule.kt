package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.HomeActivityUI
import com.hefesto.juntasaccioncomunal.ui.activities.home.HomeActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    fun providesHomActivityViewModel(homeActivityUI: HomeActivityUI) : HomeActivityViewModel = HomeActivityViewModel(homeActivityUI = homeActivityUI)

}