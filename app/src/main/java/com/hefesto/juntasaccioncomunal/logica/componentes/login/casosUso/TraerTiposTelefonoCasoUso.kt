package com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.TipoTelefonoModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoTelefono
import javax.inject.Inject

interface TraerTiposTelefonoCasoUso {
    fun invoke(): MutableLiveData<List<TipoTelefonoModel>>
}

class TraerTiposTelefonoCasoUsoImpl constructor(
    @JvmField @Inject var context: Context
): TraerTiposTelefonoCasoUso {

    private val listaTiposTelefono = MutableLiveData<List<TipoTelefonoModel>>()

    override fun invoke(): MutableLiveData<List<TipoTelefonoModel>> {
        llenarLista()
        return listaTiposTelefono
    }

    //region metodos privados
    private fun llenarLista() {
        val lista = emptyList<TipoTelefonoModel>().toMutableList()
        TipoTelefono.values().forEach { lista.add(TipoTelefonoModel(tipoTelefono = it,  context.getString(it.traerStringRes()))) }
        listaTiposTelefono.postValue(lista)
    }
    //endregion
}