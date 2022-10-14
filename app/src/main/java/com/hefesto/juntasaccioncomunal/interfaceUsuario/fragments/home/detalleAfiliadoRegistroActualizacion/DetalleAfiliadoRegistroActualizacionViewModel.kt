package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.home.detalleAfiliadoRegistroActualizacion

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.home.ui.registroAfiliado.DetalleAfiliadoRegistroActualizacionUI
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.ManejarErrores
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetalleAfiliadoRegistroActualizacionViewModel constructor(
    @JvmField @Inject var detalleAfiliadoRegistroActualizacionUI: DetalleAfiliadoRegistroActualizacionUI
)  : BaseViewModel(){

    //region variables
    private val finalizacionRegistroLiveData = MutableLiveData<Boolean?>()
    //endregion

    override fun traerBaseUI(): BaseUI = detalleAfiliadoRegistroActualizacionUI

    fun registrarDatos(compilacionDatos: CompiladoInformacionAfiliadoParaRegistroModel) : MutableLiveData<Boolean?> {
        GlobalScope.launch {
            detalleAfiliadoRegistroActualizacionUI
                .registrarAfiliado(compiladoInformacionAfiliadoParaRegistroModel = compilacionDatos)
                .ManejarErrores(escuchadorErrores = detalleAfiliadoRegistroActualizacionUI.traerEscuchadorExcepciones())
                .collect {
                    finalizacionRegistroLiveData.postValue(it)
                }
        }
        return finalizacionRegistroLiveData
    }

}