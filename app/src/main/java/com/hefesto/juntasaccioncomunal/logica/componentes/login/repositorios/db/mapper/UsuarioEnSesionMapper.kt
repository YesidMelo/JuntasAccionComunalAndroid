package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.login.AfiliadoEnSesionView
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.login.JACEnSesionView
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.AfiliadoEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.JACEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion

fun AfiliadoEnSesionView.convertirAAfiliadoEnSesionModel() : AfiliadoEnSesionModel {
    return AfiliadoEnSesionModel(
        id = this.afiliadoId,
        nombres = this.nombres,
        apellidos = this.apellidos,
        correo = this.correo,
        estadoAfiliacion = EstadoAfiliacion.PRE_AFILIADO
    )
}

fun JACEnSesionView.convertirAJACEnSesionModel(): JACEnSesionModel {
    return JACEnSesionModel(
        jacID = this.jacID,
        nombre = this.nombreJAC,
        codigoJAC = this.codigoJAC
    )
}