package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion

import android.content.Context
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.TipoReunionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface TraerTiposReunionCasoUso  {
    fun invoke() : Flow<List<TipoReunionModel>>
}

class TraerTiposReunionCasoUsoImpl constructor(
    @JvmField @Inject var context: Context
) : TraerTiposReunionCasoUso {

    override fun invoke(): Flow<List<TipoReunionModel>> = flow {
        val lista = emptyList<TipoReunionModel>().toMutableList()
        for (tipo in TipoReunion.values()) {
            lista.add(TipoReunionModel(tipoReunion = tipo, nombre = context.getString(tipo.traerStringRes())))
        }
        emit(lista)
    }

}