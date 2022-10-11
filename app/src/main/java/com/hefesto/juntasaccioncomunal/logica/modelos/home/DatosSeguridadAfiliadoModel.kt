package com.hefesto.juntasaccioncomunal.logica.modelos.home

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class DatosSeguridadAfiliadoModel(
    var contraseniaId: Int? = null,
    var contrasenia: String? = null,
    var repetirContrasenia: String? = null
) : BaseModel()
