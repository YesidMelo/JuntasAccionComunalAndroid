package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.crearActa

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeRepositorio
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TraerListaReunionesParaCreacionActaCasoUso {
    fun invoke(): Flow<List<ReunionAsambleaCreacionActaModel>>
}

class TraerListaReunionesParaCreacionActaCasoUsoImpl constructor(
    @JvmField @Inject var homeRepositorio: HomeRepositorio
): TraerListaReunionesParaCreacionActaCasoUso {

    override fun invoke(): Flow<List<ReunionAsambleaCreacionActaModel>> = homeRepositorio.traerListaReunionesParaCrearActa()

}