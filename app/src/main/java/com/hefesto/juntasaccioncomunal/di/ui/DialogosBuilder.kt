package com.hefesto.juntasaccioncomunal.di.ui

import com.hefesto.juntasaccioncomunal.ui.dialogo.DialogoCalendario
import com.hefesto.juntasaccioncomunal.ui.dialogo.DialogoInformativo
import com.hefesto.juntasaccioncomunal.ui.dialogo.DialogoLoading
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DialogosBuilder {

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeDialogoInformativo() : DialogoInformativo

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeDialogoLoading() : DialogoLoading

}