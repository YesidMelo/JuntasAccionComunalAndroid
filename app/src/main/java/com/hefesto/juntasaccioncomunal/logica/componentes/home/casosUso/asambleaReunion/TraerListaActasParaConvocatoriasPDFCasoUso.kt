package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeRepositorio
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.ReunionParaGenerarConvocatoriaPDFModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TraerListaActasParaConvocatoriasPDFCasoUso {
    fun invoke(): Flow<List<ReunionParaGenerarConvocatoriaPDFModel>>
}

class TraerListaActasParaConvocatoriasPDFCasoUsoImpl constructor(
    @JvmField @Inject var homeRepositorio: HomeRepositorio
) : TraerListaActasParaConvocatoriasPDFCasoUso {

    override fun invoke(): Flow<List<ReunionParaGenerarConvocatoriaPDFModel>>
    = homeRepositorio.traerListaReunionesParaConvocatoriasPDF()

}