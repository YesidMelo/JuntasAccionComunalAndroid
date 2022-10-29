package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.agendarReunion

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeRepositorio
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.ConvocanteReunionAsambleaAAgendarModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface TraerListaAfiliadosDisponiblesParaConvocarCasoUso {
    fun invoke() : Flow<List<ConvocanteReunionAsambleaAAgendarModel>>
}

class TraerListaAfiliadosDisponiblesParaConvocarCasoUsoImpl constructor(
    @JvmField @Inject var homeRepositorio: HomeRepositorio
) : TraerListaAfiliadosDisponiblesParaConvocarCasoUso {

    override fun invoke(): Flow<List<ConvocanteReunionAsambleaAAgendarModel>>
    = homeRepositorio.traerListaPosiblesConvocantesReunion()

}