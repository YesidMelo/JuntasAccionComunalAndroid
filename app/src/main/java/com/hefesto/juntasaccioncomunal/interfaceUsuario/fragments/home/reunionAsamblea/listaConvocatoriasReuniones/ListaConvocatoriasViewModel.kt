package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.listaConvocatoriasReuniones

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion.ListaConvocatoriasReunionUI
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.ReunionParaGenerarConvocatoriaPDFModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListaConvocatoriasViewModel constructor(
    @JvmField @Inject var listaConvocatoriasReunionUI: ListaConvocatoriasReunionUI
) : BaseViewModel() {

    //region variables
    private val listaReunionesLiveData = MutableLiveData<List<ReunionParaGenerarConvocatoriaPDFModel>>()
    //endregion

    override fun traerBaseUI(): BaseUI = listaConvocatoriasReunionUI

    fun cargarListaReuniones() {
        GlobalScope.launch {
            listaConvocatoriasReunionUI
                .traerListaActasParaConvocatoria()
                .collect{listaReunionesLiveData.postValue(it)}
        }
    }

    fun traerListaReunionesLiveData(): MutableLiveData<List<ReunionParaGenerarConvocatoriaPDFModel>> = listaReunionesLiveData
}