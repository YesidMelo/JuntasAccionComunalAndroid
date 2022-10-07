package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.login.AfiliadoEnSesionView
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.AfiliadoEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel

fun AfiliadoEnSesionView.convertirAAfiliadoEnSesionModel() : AfiliadoEnSesionModel {
    return AfiliadoEnSesionModel(
        id = this.afiliadoId,
        nombres = this.nombres,
        apellidos = this.apellidos,
        correo = this.correo
    )
}