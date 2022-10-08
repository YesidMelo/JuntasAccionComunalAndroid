package com.hefesto.juntasaccioncomunal.interfaceUsuario.fragments.login.registrarJAC

import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.RegistrarJACFragmentUI
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel
import com.hefesto.juntasaccioncomunal.interfaceUsuario.base.BaseViewModel
import javax.inject.Inject

class RegistrarJACFragmentViewModel constructor(
    @JvmField @Inject var registrarJACFragmentUI: RegistrarJACFragmentUI
) : BaseViewModel(){

    override fun traerBaseUI(): BaseUI = registrarJACFragmentUI

    fun registrarJAC(jacRegistroModel: JACRegistroModel) = registrarJACFragmentUI.registrarJAC(jacRegistroModel = jacRegistroModel)

}