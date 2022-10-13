package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado

import android.content.Context
import com.hefesto.juntasaccioncomunal.logica.modelos.general.TipoDocumentoModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CargarTiposDocumentoCasoUso {
    fun invoke(): Flow<List<TipoDocumentoModel>>
}

class CargarTiposDocumentoCasoUsoImpl constructor(
    @JvmField @Inject var context: Context
) : CargarTiposDocumentoCasoUso {

    override fun invoke(): Flow<List<TipoDocumentoModel>> = flow {
        val lista = emptyList<TipoDocumentoModel>().toMutableList()
        for (tipo in TipoDocumento.values()) {
            lista.add(TipoDocumentoModel(tipoDocumento = tipo, nombre = context.getString(tipo.traerStringRes())))
        }
        emit(lista)
    }

}