package com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion

import com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso.CargarEscuchadorExcepcionesCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.crearActa.TraerListaAfiliadosParaAsistenciaCasoUso
import com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.guardarActa.GuardarActaReunionCasoUso
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.AfiliadoActaAsistenciaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CrearActaUI constructor(
    @JvmField @Inject var escuchadorExcepcionesCasoUso: CargarEscuchadorExcepcionesCasoUso,
    @JvmField @Inject var traerListaAfiliadosParaAsistenciaCasoUso: TraerListaAfiliadosParaAsistenciaCasoUso,
    @JvmField @Inject var guardarActaReunionCasoUso : GuardarActaReunionCasoUso
) : BaseUI (cargarEscuchadorExcepcionesCasoUso = escuchadorExcepcionesCasoUso) {

    fun traerEscuchadorExcepciones() = escuchadorExcepcionesCasoUso.invoke()

    fun traerListaParaIngresarAsistencia() = traerListaAfiliadosParaAsistenciaCasoUso.invoke()

    fun guardarActa(asistencia: MutableList<AfiliadoActaAsistenciaModel>, detalleReunion: ReunionAsambleaCreacionActaModel)
    = guardarActaReunionCasoUso.invoke(asistencia = asistencia, detalleReunion = detalleReunion)
}