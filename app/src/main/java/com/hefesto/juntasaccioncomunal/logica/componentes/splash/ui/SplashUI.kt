package com.hefesto.juntasaccioncomunal.logica.componentes.splash.ui

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.casosUso.PrecargaAplicacionFinalizadaCasoUso
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SplashUI constructor(
    @JvmField @Inject var precargaAplicacionFinalizadaCasoUso: PrecargaAplicacionFinalizadaCasoUso,
    @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones){

    fun iniciarPrecargaAplicacion() : Flow<Boolean> = precargaAplicacionFinalizadaCasoUso.invoke()

}