package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.guardarActa

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeRepositorio
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.AfiliadoActaAsistenciaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GuardarActaReunionCasoUso {
    fun invoke(asistencia: MutableList<AfiliadoActaAsistenciaModel>, detalleReunion: ReunionAsambleaCreacionActaModel) : Flow<Boolean>
}

class GuardarActaReunionCasoUsoImpl constructor(
    @JvmField @Inject var homeRepositorio: HomeRepositorio
) : GuardarActaReunionCasoUso {

    override fun invoke(
        asistencia: MutableList<AfiliadoActaAsistenciaModel>,
        detalleReunion: ReunionAsambleaCreacionActaModel
    ): Flow<Boolean> = flow {
        homeRepositorio.guardarActa(asistencia = asistencia, detalleReunion = detalleReunion)
    }

}
