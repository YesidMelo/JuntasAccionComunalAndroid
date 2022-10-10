package com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class UsuarioInicioSesionModel (
    val correo: String? ,
    val contrasenia: String?
) : BaseModel()