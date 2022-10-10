package com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.modelos.general.TipoDocumentoModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento
import javax.inject.Inject

interface TraerTipoDocumentosCasoUso {
    fun invoke(): MutableLiveData<List<TipoDocumentoModel>>
}

class TraerTipoDocumentosCasoUsoImpl constructor(
    @JvmField @Inject var context: Context
) : TraerTipoDocumentosCasoUso {

    //region variables
    private val listaDocumentosLiveData = MutableLiveData<List<TipoDocumentoModel>>()
    //endregion

    override fun invoke(): MutableLiveData<List<TipoDocumentoModel>> {
        llenarLista()
        return listaDocumentosLiveData
    }

    //region metodos privados
    private fun llenarLista() {
        val lista = emptyList<TipoDocumentoModel>().toMutableList()
        TipoDocumento.values().forEach { lista.add(TipoDocumentoModel(tipoDocumento = it, context.getString(it.traerStringRes()))) }
        listaDocumentosLiveData.postValue(lista)
    }
    //endregion
}