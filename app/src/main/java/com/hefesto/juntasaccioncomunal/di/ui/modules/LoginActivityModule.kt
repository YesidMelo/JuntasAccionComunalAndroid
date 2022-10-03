package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.IniciarSesionFragmentUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.LoginActivityUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.RegistrarAfiliadoFragmentUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.RegistrarJACFragmentUI
import com.hefesto.juntasaccioncomunal.ui.activities.login.LoginActivityViewModel
import com.hefesto.juntasaccioncomunal.ui.fragments.login.iniciarSesion.IniciarSesionFragmentViewModel
import com.hefesto.juntasaccioncomunal.ui.fragments.login.registrarAfiliado.RegistrarAfiliadoFragmentViewModel
import com.hefesto.juntasaccioncomunal.ui.fragments.login.registrarJAC.RegistrarJACFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {

    @Provides
    fun providesLoginActivityViewModel(loginActivityUI: LoginActivityUI)
    = LoginActivityViewModel(loginActivityUI = loginActivityUI)

    @Provides
    fun providesIniciarSesionFragmentViewModel(iniciarSesionFragmentUI: IniciarSesionFragmentUI)
    = IniciarSesionFragmentViewModel(iniciarSesionFragmentUI = iniciarSesionFragmentUI)

    @Provides
    fun providesRegistrarJACFragmentViewModel(registrarJACFragmentUI: RegistrarJACFragmentUI)
    = RegistrarJACFragmentViewModel(registrarJACFragmentUI = registrarJACFragmentUI)

    @Provides
    fun providesRegistrarAfiliadoFragmentViewModel(registrarAfiliadoFragmentUI: RegistrarAfiliadoFragmentUI)
    = RegistrarAfiliadoFragmentViewModel(registrarAfiliadoUI = registrarAfiliadoFragmentUI)
}