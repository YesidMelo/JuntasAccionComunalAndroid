package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.generarActaPdf

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion.GenerarActaPDFUI
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.ManejarErrores
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class GenerarActaPdfViewModel constructor(
    @JvmField @Inject var generarActaPDFUI: GenerarActaPDFUI
): BaseViewModel() {

    //region variables
    private val listaReunionesParaPDF = MutableLiveData<List<ReunionParaGenerarPDFModel>>()
    private val haCargadoLiveData = MutableLiveData<Boolean>()
    //endregion

    override fun traerBaseUI(): BaseUI = generarActaPDFUI

    fun cargarListaReuniones() {
        GlobalScope.launch {
        haCargadoLiveData.postValue(false)
            generarActaPDFUI
                .listaActasParaGenerarPDF()
                .ManejarErrores(escuchadorErrores = generarActaPDFUI.traerEscuchadorExcepciones())
                .collect{
                    listaReunionesParaPDF.postValue(it?: emptyList())
                    haCargadoLiveData.postValue(true)
                }
        }
    }

    fun traerListaREunionesLiveData() = listaReunionesParaPDF

    fun traerHaCargadoLiveData() = haCargadoLiveData
}