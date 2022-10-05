package com.hefesto.juntasaccioncomunal.di

import com.hefesto.juntasaccioncomunal.MiAplicacion
import com.hefesto.juntasaccioncomunal.di.fuenteDatos.BaseDatosModule
import com.hefesto.juntasaccioncomunal.di.fuenteDatos.CacheModule
import com.hefesto.juntasaccioncomunal.di.logica.CasosUsoModule
import com.hefesto.juntasaccioncomunal.di.logica.DatasourcesModule
import com.hefesto.juntasaccioncomunal.di.logica.HelpersDBDatasourceModule
import com.hefesto.juntasaccioncomunal.di.logica.RepositoriosModule
import com.hefesto.juntasaccioncomunal.di.logica.UiModule
import com.hefesto.juntasaccioncomunal.di.ui.ActivityBuilder
import com.hefesto.juntasaccioncomunal.di.ui.DialogosBuilder
import com.hefesto.juntasaccioncomunal.di.ui.FragmentBuilder
import com.hefesto.juntasaccioncomunal.di.ui.modules.NavegacionModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [
    //fuenteDatos
    CacheModule::class,
    BaseDatosModule::class,
    //logica
    HelpersDBDatasourceModule::class,
    DatasourcesModule::class,
    RepositoriosModule::class,
    CasosUsoModule::class,
    UiModule::class,
    //ui
    NavegacionModule::class,
    AndroidInjectionModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class,
    DialogosBuilder::class

])
interface AplicacionComponent : AndroidInjector<MiAplicacion>{

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MiAplicacion>()
}