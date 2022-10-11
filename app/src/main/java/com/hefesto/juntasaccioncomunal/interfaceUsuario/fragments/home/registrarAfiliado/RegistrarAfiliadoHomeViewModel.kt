package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.registrarAfiliado

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.RegistroActualizacionAfiliadoUI
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.ManejarErrores
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrarAfiliadoHomeViewModel constructor(
    @JvmField @Inject var registroActualizacionAfiliadoUI: RegistroActualizacionAfiliadoUI
) : BaseViewModel(){

    //region variables
    private val listaAfiliadosRegistroActualizacionLiveData = MutableLiveData<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?>()
    //endregion

    override fun traerBaseUI(): BaseUI = registroActualizacionAfiliadoUI

    fun traerListaAfiliadosRegistroActualizacion() : MutableLiveData<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?> {
        GlobalScope.launch {
            registroActualizacionAfiliadoUI
                .traerListaAfiliadosActualizacionRegistro()
                .ManejarErrores(escuchadorErrores = registroActualizacionAfiliadoUI.traerEscuchadorExcepciones())
                .collect{ listaAfiliadosRegistroActualizacionLiveData.postValue(it) }
        }
        return this.listaAfiliadosRegistroActualizacionLiveData
    }
}