package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.TraerTiposReunionCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.agendarReunion.AgendarReunionAsambleaCasoUso
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.DetalleReunionAAgendarModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AgendarReunionUI constructor(
    @JvmField @Inject var cargarEscuchadorExcepciones: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var traerTiposReunionCasoUso: TraerTiposReunionCasoUso,
    @JvmField @Inject var agendarReunionAsambleaCasoUso: AgendarReunionAsambleaCasoUso,
) : BaseUI(cargarEscuchadorExcepcionesCasoUso = cargarEscuchadorExcepciones) {

    fun traerEscuchadorExcepciones() = cargarEscuchadorExcepciones.invoke()

    fun traerListaTipoReunion() = traerTiposReunionCasoUso.invoke()

    fun agendarReunion(detalleReunionAAgendarModel: DetalleReunionAAgendarModel) = agendarReunionAsambleaCasoUso.invoke(detalleReunionAAgendarModel = detalleReunionAAgendarModel)
}