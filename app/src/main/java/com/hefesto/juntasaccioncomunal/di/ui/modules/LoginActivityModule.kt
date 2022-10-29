package com.hefesto.juntasaccioncomunal.di.ui.modules

import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.IniciarSesionFragmentUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.LoginActivityUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.RegistrarAfiliadoFragmentUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.RegistrarJACFragmentUI
import com.hefesto.juntasaccioncomunal.interfaceUsuario.activities.login.LoginActivityViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.iniciarSesion.IniciarSesionFragmentViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.registrarAfiliado.RegistrarAfiliadoFragmentViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.registrarJAC.RegistrarJACFragmentViewModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.utilidades.gestorPermisos.GestorPermisos
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {

    //region permisos
    @Provides
    fun providesGestorPermisos() : GestorPermisos = GestorPermisos()

    //endregion

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