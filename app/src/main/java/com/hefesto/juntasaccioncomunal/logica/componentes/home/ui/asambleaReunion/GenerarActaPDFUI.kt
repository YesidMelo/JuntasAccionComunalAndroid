package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import javax.inject.Inject

class GenerarActaPDFUI constructor(
    @JvmField @Inject var escuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso
) :  BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepcionesCasoUso) {

    fun traerEscuchadorExcepciones() = escuchadorExcepcionesCasoUso.invoke()

}