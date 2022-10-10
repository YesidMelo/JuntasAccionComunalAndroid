package com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion

data class AfiliadoEnSesionModel (
    var id: Int,
    var nombres: String,
    var apellidos: String,
    var correo: String,
    var estadoAfiliacion: EstadoAfiliacion
) : BaseModel()