package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.TraerTiposReunionCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.crearActa.TraerListaReunionesParaCreacionActaCasoUso
import javax.inject.Inject

class ListaReunionesCreacionActaUI constructor(
    @JvmField @Inject var escuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var traerListaReunionesParaCreacionActaCasoUso: TraerListaReunionesParaCreacionActaCasoUso
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = escuchadorExcepcionesCasoUso) {

    fun traerEscuchadorExcepciones() = escuchadorExcepcionesCasoUso.invoke()
    fun traerListaReunionesParaCreacionActas() = traerListaReunionesParaCreacionActaCasoUso.invoke()
}