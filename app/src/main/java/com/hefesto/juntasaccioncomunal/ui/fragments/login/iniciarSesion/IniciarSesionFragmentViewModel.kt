package com.hefesto.juntasaccioncomunal.ui.fragments.login.iniciarSesion

import androidx.lifecycle.ViewModel
import com.hefesto.juntasaccioncomunal.logica.componentes.login.ui.IniciarSesionFragmentUI
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import javax.inject.Inject

class IniciarSesionFragmentViewModel constructor(
    @JvmField @Inject var iniciarSesionFragmentUI: IniciarSesionFragmentUI
) : ViewModel() {

    fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel) = iniciarSesionFragmentUI.iniciarSesion(usuarioInicioSesionModel = usuarioInicioSesionModel)

}