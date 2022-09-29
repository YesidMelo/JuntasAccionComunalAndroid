package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.LoginActivityUI
import com.hefesto.juntasaccioncomunal.ui.activities.login.LoginActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {

    @Provides
    fun providesLoginActivityViewModel(loginActivityUI: LoginActivityUI) = LoginActivityViewModel(loginActivityUI = loginActivityUI)
}