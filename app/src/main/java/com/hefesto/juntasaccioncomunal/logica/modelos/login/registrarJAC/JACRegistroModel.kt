package com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC

data class JACRegistroModel(
    var NombreJAC : String? = null,
    var CodigoJAC : String? = null,
    var Correo: String? = null,
    var Contrasenia: String? = null,
    var RepetirContrasenia: String? = null,
)
