package com.hefesto.juntasaccioncomunal.logica.componentes.login.ui

import com.hefesto.juntasaccioncomunal.logica.componentes.login.casosUso.IniciarSesionCasoUso
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import javax.inject.Inject

class IniciarSesionFragmentUI constructor(
    @JvmField @Inject var iniciarSesionCasoUso: IniciarSesionCasoUso
) {

    fun iniciarSesion(usuarioInicioSesionModel: UsuarioInicioSesionModel) = iniciarSesionCasoUso.invoke(usuarioInicioSesionModel = usuarioInicioSesionModel)
}