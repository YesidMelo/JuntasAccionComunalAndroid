package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.crearActa

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeRepositorio
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.AfiliadoActaAsistenciaModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TraerListaAfiliadosParaAsistenciaCasoUso {
    fun invoke() : Flow<List<AfiliadoActaAsistenciaModel>>
}

class TraerListaAfiliadosParaAsistenciaCasoUsoImpl constructor(
    @JvmField @Inject var homeRepositorio: HomeRepositorio
) : TraerListaAfiliadosParaAsistenciaCasoUso {

    override fun invoke(): Flow<List<AfiliadoActaAsistenciaModel>>
    = homeRepositorio.traerListaAfiliadosAsistencia()

}