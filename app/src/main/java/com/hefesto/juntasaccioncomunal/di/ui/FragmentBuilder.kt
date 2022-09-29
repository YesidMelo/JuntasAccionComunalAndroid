package com.hefesto.juntasaccioncomunal.di.ui

import com.hefesto.juntasaccioncomunal.di.ui.modules.LoginActivityModule
import com.hefesto.juntasaccioncomunal.ui.fragments.login.iniciarSesion.IniciarSesionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    //region login
    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributeIniciarSesionFragment() : IniciarSesionFragment
    //endregion
}