package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.detalleEnJACAfiliado

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.DetalleAfiliadoEnJacUI
import com.hefesto.juntasaccioncomunal.logica.modelos.general.ComiteEnJacModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.ManejarErrores
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetalleEnJacViewModel constructor(
    @JvmField @Inject var detalleAfiliadoEnJacUI: DetalleAfiliadoEnJacUI
) : BaseViewModel() {

    //region variables
    private val listaComitesLiveData = MutableLiveData<List<ComiteEnJacModel>?>()
    //endregion


    override fun traerBaseUI(): BaseUI = detalleAfiliadoEnJacUI

    fun cargarComitesEnJac() : MutableLiveData<List<ComiteEnJacModel>?> {
        GlobalScope.launch {
            detalleAfiliadoEnJacUI
                .traerListaComitesEnJac()
                .ManejarErrores(escuchadorErrores = detalleAfiliadoEnJacUI.traerEscuchadorExcepciones())
                .collect{ listaComitesLiveData.postValue(it) }
        }
        return listaComitesLiveData
    }
}