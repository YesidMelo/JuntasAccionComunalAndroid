package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.TraerListaActasParaConvocatoriasPDFCasoUso
import javax.inject.Inject

class ListaConvocatoriasReunionUI constructor(
    @JvmField @Inject var escuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var traerListaActasParaConvocatoriasPDFCasoUso: TraerListaActasParaConvocatoriasPDFCasoUso,
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepcionesCasoUso) {

    fun traerEscuchadorExcepciones() = escuchadorExcepcionesCasoUso.invoke()

    fun traerListaActasParaConvocatoria() = traerListaActasParaConvocatoriasPDFCasoUso.invoke()
}