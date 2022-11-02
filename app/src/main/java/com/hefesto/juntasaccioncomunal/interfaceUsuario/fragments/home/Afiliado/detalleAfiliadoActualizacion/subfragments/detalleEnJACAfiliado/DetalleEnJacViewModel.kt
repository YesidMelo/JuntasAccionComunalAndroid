package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.Afiliado.detalleAfiliadoActualizacion.subfragments.detalleEnJACAfiliado

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.DetalleAfiliadoEnJacUI
import com.hefesto.juntasaccioncomunal.logica.modelos.general.ComiteEnJacModel
import com.hefesto.juntasaccioncomunal.logica.modelos.general.EstadoAfiliadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.DetalleEnJACParaRegistroModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.ComitesEnJAC
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.ManejarErrores
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetalleEnJacViewModel constructor(
    @JvmField @Inject var detalleAfiliadoEnJacUI: DetalleAfiliadoEnJacUI
) : BaseViewModel() {

    //region variables
    private lateinit var comitesEnJAC: ComitesEnJAC
    private lateinit var estadoAfiliacion: EstadoAfiliacion
    private val listaEstadosAfiliacionLiveData = MutableLiveData<List<EstadoAfiliadoModel>?>()
    private val listaComitesLiveData = MutableLiveData<List<ComiteEnJacModel>?>()
    private val datosBasicosAfiliadoActualizarRegistrarInformacionModelLiveData = MutableLiveData<DatosBasicosAfiliadoActualizarRegistrarInformacionModel?>()
    //endregion


    override fun traerBaseUI(): BaseUI = detalleAfiliadoEnJacUI

    fun conComiteSeleccionado(comitesEnJAC: ComitesEnJAC) : DetalleEnJacViewModel {
        this.comitesEnJAC = comitesEnJAC
        return this
    }

    fun conEstadoAfiliacion(estadoAfiliacion: EstadoAfiliacion) : DetalleEnJacViewModel {
        this.estadoAfiliacion = estadoAfiliacion
        return this
    }

    fun traerDetalleEnJACParaRegistroModel() : DetalleEnJACParaRegistroModel {
        return DetalleEnJACParaRegistroModel(
            comitesEnJAC = comitesEnJAC,
            estadoAfiliacion = estadoAfiliacion
        )
    }

    fun conDatosBasicosAfiliadoActualizarRegistrarInformacionModel(datosBasicosAfiliadoActualizarRegistrarInformacionModel : DatosBasicosAfiliadoActualizarRegistrarInformacionModel?) : DetalleEnJacViewModel {
        datosBasicosAfiliadoActualizarRegistrarInformacionModelLiveData.postValue(datosBasicosAfiliadoActualizarRegistrarInformacionModel)
        return this
    }

    fun cargarDetalleUsuarioEnJAC() = datosBasicosAfiliadoActualizarRegistrarInformacionModelLiveData

    fun cargarComitesEnJac() : MutableLiveData<List<ComiteEnJacModel>?> {
        GlobalScope.launch {
            detalleAfiliadoEnJacUI
                .traerListaComitesEnJac()
                .ManejarErrores(escuchadorErrores = detalleAfiliadoEnJacUI.traerEscuchadorExcepciones())
                .collect{ listaComitesLiveData.postValue(it) }
        }
        return listaComitesLiveData
    }

    fun cargarEstadosAfiliacion(): MutableLiveData<List<EstadoAfiliadoModel>?> {
        GlobalScope.launch {
            detalleAfiliadoEnJacUI
                .traerListaEstadosAfiliado()
                .ManejarErrores(escuchadorErrores = detalleAfiliadoEnJacUI.traerEscuchadorExcepciones())
                .collect {listaEstadosAfiliacionLiveData.postValue(it)}
        }
        return listaEstadosAfiliacionLiveData
    }

}