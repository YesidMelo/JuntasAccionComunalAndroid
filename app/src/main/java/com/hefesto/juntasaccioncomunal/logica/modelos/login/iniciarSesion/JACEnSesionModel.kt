package com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class JACEnSesionModel (
    var jacID: Int,
    var nombre: String,
    var codigoJAC: String,
) : BaseModel()