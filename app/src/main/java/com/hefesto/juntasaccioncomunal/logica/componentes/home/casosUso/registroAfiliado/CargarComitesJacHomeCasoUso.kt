package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado

import android.content.Context
import com.hefesto.juntasaccioncomunal.logica.modelos.general.ComiteEnJacModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.ComitesEnJAC
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CargarComitesJacHomeCasoUso {
    fun invoke(): Flow<List<ComiteEnJacModel>?>
}

class CargarComitesJacHomeCasoUsoImpl constructor(
    @JvmField @Inject var context: Context
): CargarComitesJacHomeCasoUso {

    override fun invoke(): Flow<List<ComiteEnJacModel>?> = flow {
        val lista = emptyList<ComiteEnJacModel>().toMutableList()
        ComitesEnJAC.values().forEach {
            lista.add(ComiteEnJacModel(comitesEnJAC = it, nombre = context.getString(it.traerStringRes())))
        }
        emit(lista)
    }

}