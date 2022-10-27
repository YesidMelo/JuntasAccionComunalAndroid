package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeRepositorio
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TraerListaActasParaGenerarPDFCasoUso {
    fun invoke(): Flow<List<ReunionParaGenerarPDFModel>>
}

class TraerListaActasParaGenerarPDFCasoUsoImpl constructor(
    @JvmField @Inject var homeRepositorio: HomeRepositorio
) : TraerListaActasParaGenerarPDFCasoUso {

    override fun invoke(): Flow<List<ReunionParaGenerarPDFModel>>
    = homeRepositorio.traerListaReunionesParaGenerarPDF()

}