package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.reunionAsamblea.agendarReunion

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.asambleaReunion.AgendarReunionUI
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.DetalleReunionAAgendarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.PuntoReunionAgendarReunionAsambleaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.TipoReunionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.ManejarErrores
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class AgendarReunionViewModel constructor(
    @JvmField @Inject var agendarReunionUI: AgendarReunionUI
): BaseViewModel(){

    //region variables
    private val terminoCargaLiveData = MutableLiveData<Boolean>()
    private val listaTipoReunionLiveData = MutableLiveData<List<TipoReunionModel>>()
    private val fechaReunionLiveData = MutableLiveData<Date?>()
    private val horaReunionLiveData = MutableLiveData<Date?>()
    private val adicionarPuntoLiveData = MutableLiveData<Boolean>()
    //endregion

    override fun traerBaseUI(): BaseUI = agendarReunionUI

    fun adicionarFechaSeleccionada(date: Date?) {
        fechaReunionLiveData.postValue(date)
    }

    fun adicionarHoraSeleccionada(hora: Date?) {
        horaReunionLiveData.postValue(hora)
    }

    fun adicionarPuntoAReunion(adicionar: Boolean) {
        adicionarPuntoLiveData.postValue(adicionar)
    }

    fun agendarReunion(
        tituloReunion: String,
        tipoReunion: TipoReunion,
        listaPuntosReunion : List<PuntoReunionAgendarReunionAsambleaModel>
    ) {
        GlobalScope.launch {
            agendarReunionUI
                .agendarReunion(
                    detalleReunionAAgendarModel = DetalleReunionAAgendarModel(
                        tituloReunion = tituloReunion,
                        tipoReunion = tipoReunion,
                        fechaReunion = fechaReunionLiveData.value,
                        horaReunion = horaReunionLiveData.value,
                        puntosReunion = listaPuntosReunion
                    )
                )
                .ManejarErrores(escuchadorErrores = agendarReunionUI.traerEscuchadorExcepciones())
                .collect{ terminoCargaLiveData.postValue(it) }
        }
    }

    fun traerTiposReunionLiveData() : MutableLiveData<List<TipoReunionModel>> {
        GlobalScope.launch {
            agendarReunionUI
                .traerListaTipoReunion()
                .ManejarErrores(escuchadorErrores = agendarReunionUI.traerEscuchadorExcepciones())
                .collect{listaTipoReunionLiveData.postValue(it)}
        }
        return listaTipoReunionLiveData
    }

    fun traerfechaReunionLiveData() : MutableLiveData<Date?> = fechaReunionLiveData

    fun traerHoraReunionLiveData() : MutableLiveData<Date?> = horaReunionLiveData

    fun traerAdicionarPuntoLiveData() : MutableLiveData<Boolean> = adicionarPuntoLiveData

    fun traerTerminoCargaLiveData() : MutableLiveData<Boolean> = terminoCargaLiveData

}