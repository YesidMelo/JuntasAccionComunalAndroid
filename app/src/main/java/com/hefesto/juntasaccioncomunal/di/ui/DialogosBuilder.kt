package com.hefesto.juntasaccioncomunal.di.ui


import com.hefesto.juntasaccioncomunal.ui.dialogo.DialogoInformativo
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DialogosBuilder {

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeDialogoInformativo() : DialogoInformativo
}