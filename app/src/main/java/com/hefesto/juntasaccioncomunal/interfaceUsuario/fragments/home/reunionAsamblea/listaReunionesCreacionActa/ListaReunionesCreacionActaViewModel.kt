package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaReunionesCreacionActa

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion.ListaReunionesCreacionActaUI
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.ManejarErrores
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListaReunionesCreacionActaViewModel constructor(
    @JvmField @Inject var listaReunionesCreacionActaUI: ListaReunionesCreacionActaUI
) : BaseViewModel() {

    //region variables
    private val haCargadoLiveData = MutableLiveData<Boolean>()
    private val listaReunionesCrearActaLiveData = MutableLiveData<List<ReunionAsambleaCreacionActaModel>>()
    //endregion

    override fun traerBaseUI(): BaseUI = listaReunionesCreacionActaUI

    //region metodos publicos

    fun cargarListaReunionesParaCreacionActas() {
        GlobalScope.launch {
            haCargadoLiveData.postValue(false)
            listaReunionesCreacionActaUI
                .traerListaReunionesParaCreacionActas()
                .ManejarErrores(escuchadorErrores = listaReunionesCreacionActaUI.traerEscuchadorExcepciones())
                .collect{
                    listaReunionesCrearActaLiveData.postValue(it?: emptyList())
                    haCargadoLiveData.postValue(true)
                }
        }
    }

    fun traerHaCargadoLiveData() = haCargadoLiveData

    fun traerListaReunionesCrearActaLiveData() = listaReunionesCrearActaLiveData

    //endregion

    //region metodos privados
    //endregion
}