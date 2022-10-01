package com.hefesto.juntasaccioncomunal.ui.fragments.login.registrarJAC

import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.IniciarSesionFragmentUI
import com.hefesto.juntasaccioncomunal.ui.base.BaseViewModel
import javax.inject.Inject

class RegistrarJACFragmentViewModel : BaseViewModel(){

    @Inject
    lateinit var iniciarSesionFragmentUI: IniciarSesionFragmentUI

    override fun traerBaseUI(): BaseUI = iniciarSesionFragmentUI
}