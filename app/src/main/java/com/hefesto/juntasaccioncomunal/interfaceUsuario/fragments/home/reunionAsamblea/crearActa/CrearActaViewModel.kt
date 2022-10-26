package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.crearActa

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion.CrearActaUI
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.AfiliadoActaAsistenciaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.ManejarErrores
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CrearActaViewModel constructor(
    @JvmField @Inject var crearActaUI: CrearActaUI
) : BaseViewModel() {

    //region variables
    var listaAfiliadosAsistieron = emptyList<AfiliadoActaAsistenciaModel>().toMutableList()
    private val haCargadoLiveData = MutableLiveData<Boolean>()
    private val detalleReunionLiveData = MutableLiveData<ReunionAsambleaCreacionActaModel?>()
    private val listaAfiliadosJAC = MutableLiveData<List<AfiliadoActaAsistenciaModel>>()
    private val actaCreadaLiveData = MutableLiveData<Boolean>()
    //endregion

    override fun traerBaseUI(): BaseUI = crearActaUI

    //region metodos publicos

    fun conReunionAModificar(detalle : ReunionAsambleaCreacionActaModel?) : CrearActaViewModel{
        detalleReunionLiveData.postValue(detalle)
        return this
    }

    fun traerListaAfiliadosJAc() {
        GlobalScope.launch {
            crearActaUI
                .traerListaParaIngresarAsistencia()
                .ManejarErrores(escuchadorErrores = crearActaUI.traerEscuchadorExcepciones())
                .collect{ listaAfiliadosJAC.postValue(it) }
        }
    }

    fun guardarActa() {
        GlobalScope.launch {
            haCargadoLiveData.postValue(false)
            crearActaUI
                .guardarActa(asistencia = listaAfiliadosAsistieron,detalleReunion = detalleReunionLiveData.value!!)
                .ManejarErrores(escuchadorErrores = crearActaUI.traerEscuchadorExcepciones())
                .collect{
                    actaCreadaLiveData.postValue(it)
                    haCargadoLiveData.postValue(true)
                }
        }
    }

    fun cargo(value: Boolean = false) {
        haCargadoLiveData.postValue(value)
    }


    fun traerDetalleReunionLiveData() = detalleReunionLiveData

    fun traerHaCargadoLiveData() = haCargadoLiveData

    fun traerListaAfiliadosJACLiveData() = listaAfiliadosJAC

    fun traerActaCreadaLiveData() = actaCreadaLiveData

    //endregion
}