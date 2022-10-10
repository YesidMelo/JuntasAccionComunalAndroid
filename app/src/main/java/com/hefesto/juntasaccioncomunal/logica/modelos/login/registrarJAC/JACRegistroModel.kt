package com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class JACRegistroModel(
    var jacId: Int?=  null,
    var NombreJAC : String? = null,
    var CodigoJAC : String? = null,
    var Correo: String? = null,
    var Contrasenia: String? = null,
    var RepetirContrasenia: String? = null,
) : BaseModel()
