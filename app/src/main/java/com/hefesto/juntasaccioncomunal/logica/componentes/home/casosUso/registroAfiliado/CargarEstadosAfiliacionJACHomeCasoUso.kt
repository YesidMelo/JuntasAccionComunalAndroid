package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado

import android.content.Context
import com.hefesto.juntasaccioncomunal.logica.modelos.general.EstadoAfiliadoModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CargarEstadosAfiliacionJACHomeCasoUso {
    fun invoke() : Flow<List<EstadoAfiliadoModel>?>
}

class CargarEstadosAfiliacionJACHomeCasoUsoImpl constructor(
    @JvmField @Inject var context: Context
) : CargarEstadosAfiliacionJACHomeCasoUso {

    override fun invoke(): Flow<List<EstadoAfiliadoModel>?> = flow {
        val lista = emptyList<EstadoAfiliadoModel>().toMutableList()
        EstadoAfiliacion.values().forEach {
            lista.add(EstadoAfiliadoModel(estadoAfiliacion = it, nombre = context.getString(it.traerStringRes())))
        }
        emit(lista)
    }

}