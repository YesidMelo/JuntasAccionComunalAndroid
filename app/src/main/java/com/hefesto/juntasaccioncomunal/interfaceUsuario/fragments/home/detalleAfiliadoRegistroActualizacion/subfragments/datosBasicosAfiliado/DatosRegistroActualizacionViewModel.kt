package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion.subfragments.datosBasicosAfiliado

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.DatosBasicosAfiliadoUI
import com.hefesto.juntasaccioncomunal.logica.modelos.general.TipoDocumentoModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.ManejarErrores
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DatosRegistroActualizacionViewModel constructor(
    @JvmField @Inject var datosBasicosAfiliadoUI: DatosBasicosAfiliadoUI
): BaseViewModel(){

    //region variables
    private val tiposDocumentoLiveData = MutableLiveData<List<TipoDocumentoModel>?>()
    //endregion

    override fun traerBaseUI(): BaseUI = datosBasicosAfiliadoUI

    fun traerTiposDocumento() : MutableLiveData<List<TipoDocumentoModel>?> {
        GlobalScope.launch {
            datosBasicosAfiliadoUI
                .traerListaTiposDocumento()
                .ManejarErrores(escuchadorErrores = datosBasicosAfiliadoUI.traerEscuchadorExcepciones())
                .collect{ tiposDocumentoLiveData.postValue(it)}
        }
        return tiposDocumentoLiveData
    }

}