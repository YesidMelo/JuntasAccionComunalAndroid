package com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp

data class UsuarioEnSesionModel(
    var rolApp : RolesEnApp? = null,
    var jacId: Int? = null,
    var funcionesRolApp: List<FuncionesRolApp>? = null,
    var jacEnSesionModel: JACEnSesionModel? = null,
    var afiliadoEnSesionModel: AfiliadoEnSesionModel? = null
) : BaseModel()