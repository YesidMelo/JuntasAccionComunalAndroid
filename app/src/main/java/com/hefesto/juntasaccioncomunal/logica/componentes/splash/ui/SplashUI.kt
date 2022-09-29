package com.hefesto.juntasaccioncomunal.logica.componentes.splash.ui

import com.hefesto.juntasaccioncomunal.logica.componentes.splash.casosUso.PrecargaAplicacionFinalizadaCasoUso
import javax.inject.Inject

class SplashUI constructor(
    @JvmField @Inject var precargaAplicacionFinalizadaCasoUso: PrecargaAplicacionFinalizadaCasoUso
) {

    fun iniciarPrecargaAplicacion() = precargaAplicacionFinalizadaCasoUso.invoke()

}