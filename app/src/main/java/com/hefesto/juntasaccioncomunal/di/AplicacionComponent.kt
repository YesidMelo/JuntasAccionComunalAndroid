package com.hefesto.juntasaccioncomunal.di

import com.hefesto.juntasaccioncomunal.MiAplicacion
import com.hefesto.juntasaccioncomunal.di.fuenteDatos.CacheModule
import com.hefesto.juntasaccioncomunal.di.ui.ActivityBuilder
import com.hefesto.juntasaccioncomunal.di.ui.modules.NavegacionModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [
    CacheModule::class,
    NavegacionModule::class,

    //ui
    AndroidInjectionModule::class,
    ActivityBuilder::class,
])
interface AplicacionComponent : AndroidInjector<MiAplicacion>{

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MiAplicacion>()
}