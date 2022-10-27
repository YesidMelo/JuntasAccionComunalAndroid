package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.TraerListaActasParaGenerarPDFCasoUso
import javax.inject.Inject

class GenerarActaPDFUI constructor(
    @JvmField @Inject var escuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var traerListaActasParaGenerarPDFCasoUso: TraerListaActasParaGenerarPDFCasoUso,
) :  BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepcionesCasoUso) {

    fun traerEscuchadorExcepciones() = escuchadorExcepcionesCasoUso.invoke()

    fun listaActasParaGenerarPDF() = traerListaActasParaGenerarPDFCasoUso.invoke()
}