package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.modelos.general.EstadoAfiliadoModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

interface TraerEstadosAfiliadoEnDirectivaCasoUso {
    fun invoke(): MutableLiveData<List<EstadoAfiliadoModel>>
}

class TraerEstadosAfiliadoEnDirectivaCasoUsoImpl constructor(
    @JvmField @Inject var context: Context
): TraerEstadosAfiliadoEnDirectivaCasoUso {

    //region variables
    private val listaEstadosAfiliadoliveData = MutableLiveData<List<EstadoAfiliadoModel>>()
    //endregion

    override fun invoke(): MutableLiveData<List<EstadoAfiliadoModel>> {
        GlobalScope.launch {
            val lista = emptyList<EstadoAfiliadoModel>().toMutableList()

            for(estadoAfiliacion in EstadoAfiliacion.values()) {
                lista.add(
                    EstadoAfiliadoModel(
                        estadoAfiliacion = estadoAfiliacion,
                        nombre = context.getString(estadoAfiliacion.traerStringRes())
                    )
                )
            }

            listaEstadosAfiliadoliveData.postValue(lista)
        }
        return listaEstadosAfiliadoliveData
    }

}