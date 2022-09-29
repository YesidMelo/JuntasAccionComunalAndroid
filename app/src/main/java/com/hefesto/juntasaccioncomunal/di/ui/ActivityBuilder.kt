package com.hefesto.juntasaccioncomunal.di.ui

import com.hefesto.juntasaccioncomunal.di.ui.modules.LoginActivityModule
import com.hefesto.juntasaccioncomunal.di.ui.modules.SplashActivityModule
import com.hefesto.juntasaccioncomunal.ui.activities.login.LoginActivity
import com.hefesto.juntasaccioncomunal.ui.activities.splash.SplashActivity
import com.hefesto.juntasaccioncomunal.ui.fragments.login.iniciarSesion.IniciarSesionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    //region login
    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributeLoginActivity() : LoginActivity

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributeIniciarSesionFragment() : IniciarSesionFragment
    //endregion

    //region splash
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract  fun contributeSplashActivity() : SplashActivity
    //endregion
}