package com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class SeguridadParaRegistroModel constructor (
    var credencialesSesionId: Int? = null,
    var contraseniaId: Int? = null,
    var contrasenia: String? = null,
    var repetirContrasenia: String? = null
) :BaseModel()