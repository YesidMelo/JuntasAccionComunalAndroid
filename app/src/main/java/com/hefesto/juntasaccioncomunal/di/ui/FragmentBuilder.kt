package com.hefesto.juntasaccioncomunal.di.ui

import com.hefesto.juntasaccioncomunal.di.ui.modules.HomeActivityModule
import com.hefesto.juntasaccioncomunal.di.ui.modules.LoginActivityModule
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.configuracionAfiliadoEnDirectiva.ConfiguracionAfiliadoEnDirectivaFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.listaAfiliadosModificacionDirectivas.ListaAfiliadosModificacionDirectivasFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.panelControl.PanelControlFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado.RegistrarAfiliadoHomeFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.iniciarSesion.IniciarSesionFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.registrarAfiliado.RegistrarAfiliadoFragment
import com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.registrarJAC.RegistrarJACFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    //region home
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesPanelControlFragment() : PanelControlFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesListaAfiliadosModificacionDirectivaFragment() : ListaAfiliadosModificacionDirectivasFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesConfiguracionAfiliadoEnDirectivaFragment() : ConfiguracionAfiliadoEnDirectivaFragment

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun providesRegistrarAfiliadoHomeFragment() : RegistrarAfiliadoHomeFragment

    //endregion


    //region login
    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributeIniciarSesionFragment() : IniciarSesionFragment

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributeRegistrarJACFragment() : RegistrarJACFragment

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributeRegistrarAfiliadoFragment() : RegistrarAfiliadoFragment
    //endregion
}