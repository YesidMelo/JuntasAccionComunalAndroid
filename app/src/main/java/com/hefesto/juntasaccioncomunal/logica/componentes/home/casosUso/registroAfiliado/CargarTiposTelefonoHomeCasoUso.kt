package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado

import android.content.Context
import com.hefesto.juntasaccioncomunal.logica.modelos.general.TipoTelefonoModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoTelefono
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CargarTiposTelefonoHomeCasoUso {
    fun invoke():Flow<List<TipoTelefonoModel>>
}

class CargarTiposTelefonoHomeCasoUsoImpl constructor(
  @JvmField @Inject var context: Context
) : CargarTiposTelefonoHomeCasoUso {

    override fun invoke(): Flow<List<TipoTelefonoModel>> = flow {
        val lista = emptyList<TipoTelefonoModel>().toMutableList()
        TipoTelefono.values().forEach {
            lista.add(TipoTelefonoModel(tipoTelefono = it, nombre = context.getString(it.traerStringRes())))
        }
        emit(lista)
    }

}
