package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.TraerTiposReunionCasoUso
import javax.inject.Inject

class AgendarReunionUI constructor(
    @JvmField @Inject var cargarEscuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var traerTiposReunionCasoUso: TraerTiposReunionCasoUso
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = cargarEscuchadorExcepciones) {

    fun traerEscuchadorExcepciones() = cargarEscuchadorExcepciones.invoke()

    fun traerListaTipoReunion() = traerTiposReunionCasoUso.invoke()
}