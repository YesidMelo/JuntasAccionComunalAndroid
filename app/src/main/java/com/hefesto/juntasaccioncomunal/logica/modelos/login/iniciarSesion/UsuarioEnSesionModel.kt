package com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion

import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp

data class UsuarioEnSesionModel(
    var rolApp : RolesEnApp? = null,
    var funcionesRolApp: List<FuncionesRolApp>? = null,
    var jacEnSesionModel: JACEnSesionModel? = null,
    var afiliadoEnSesionModel: AfiliadoEnSesionModel? = null
)