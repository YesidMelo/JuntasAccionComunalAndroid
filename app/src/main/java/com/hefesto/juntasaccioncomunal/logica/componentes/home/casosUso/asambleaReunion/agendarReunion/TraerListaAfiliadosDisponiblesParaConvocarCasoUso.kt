package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.agendarReunion

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeRepositorio
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ConvocanteReunionAsambleaModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface TraerListaAfiliadosDisponiblesParaConvocarCasoUso {
    fun invoke() : Flow<List<ConvocanteReunionAsambleaModel>>
}

class TraerListaAfiliadosDisponiblesParaConvocarCasoUsoImpl constructor(
    @JvmField @Inject var homeRepositorio: HomeRepositorio
) : TraerListaAfiliadosDisponiblesParaConvocarCasoUso {

    override fun invoke(): Flow<List<ConvocanteReunionAsambleaModel>>  = flow {
        val lista = emptyList<ConvocanteReunionAsambleaModel>().toMutableList()
        for(contador in 0 until 5) {
            val identificador = contador + 1
            var numDoc = ""
            for (t in 0 until 7) {
                numDoc += "$identificador"
            }
            lista.add(ConvocanteReunionAsambleaModel(
                afiliadoId = identificador,
                nombres = "Nombre $identificador",
                apellidos = "apellido $identificador",
                numeroDocumento = numDoc
            ))
        }
        emit(lista)
    }

}