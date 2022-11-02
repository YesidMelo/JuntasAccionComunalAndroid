package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.registrarJAC

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.RegistrarJACFragmentUI
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.ManejarErrores
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrarJACFragmentViewModel constructor(
    @JvmField @Inject var registrarJACFragmentUI: RegistrarJACFragmentUI
) : BaseViewModel(){

    //region variables
    private val registroCompleto = MutableLiveData<Boolean?>()
    //endregion

    override fun traerBaseUI(): BaseUI = registrarJACFragmentUI

    fun registrarJAC(jacRegistroModel: JACRegistroModel) : MutableLiveData<Boolean?> {
        GlobalScope.launch {
            registrarJACFragmentUI
                .registrarJAC(jacRegistroModel = jacRegistroModel)
                .ManejarErrores(escuchadorErrores = registrarJACFragmentUI.traerEscuchadorErrores())
                .collect{ registroCompleto.postValue(it)}
        }
        return registroCompleto
    }

}