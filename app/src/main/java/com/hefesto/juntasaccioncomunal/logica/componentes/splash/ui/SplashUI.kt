package com.hefesto.juntasaccioncomunal.logica.componentes.splash.ui

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.casosUso.PrecargaAplicacionFinalizadaCasoUso
import javax.inject.Inject

class SplashUI constructor(
    @JvmField @Inject var precargaAplicacionFinalizadaCasoUso: PrecargaAplicacionFinalizadaCasoUso,
    @JvmField @Inject var escuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepciones){

    fun iniciarPrecargaAplicacion() = precargaAplicacionFinalizadaCasoUso.invoke()

}