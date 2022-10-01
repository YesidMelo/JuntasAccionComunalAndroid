package com.hefesto.juntasaccioncomunal.ui.fragments.login.iniciarSesion

import com.hefesto.juntasaccioncomunal.logica.componentes.base.ui.BaseUI
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.IniciarSesionFragmentUI
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import com.hefesto.juntasaccioncomunal.ui.base.BaseViewModel
import javax.inject.Inject

class IniciarSesionFragmentViewModel constructor(
    @JvmField @Inject var iniciarSesionFragmentUI: IniciarSesionFragmentUI
) : BaseViewModel() {

    fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel) = iniciarSesionFragmentUI.iniciarSesion(usuarioInicioSesionModel = usuarioInicioSesionModel)

    override fun traerBaseUI(): BaseUI = iniciarSesionFragmentUI

}