package com.hefesto.juntasaccioncomunal.di.logica

import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.IniciarSesionCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.IniciarSesionCasoUsoImpl
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.casosUso.PrecargaAplicacionFinalizadaCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.casosUso.PrecargaAplicacionFinalizadaCasoUsoImpl
import dagger.Module
import dagger.Provides

@Module
class CasosUsoModule {

    //region login
    @Provides
    fun providesIniciarSesionCasoUso() : IniciarSesionCasoUso = IniciarSesionCasoUsoImpl()
    //endregion

    //region splash
    @Provides
    fun providesPrecargaAplicacionFinalizadaCasoUso() : PrecargaAplicacionFinalizadaCasoUso = PrecargaAplicacionFinalizadaCasoUsoImpl()
    //endregion
}