package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.AfiliadoEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.CorreosEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.DireccionesEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.TelefonosEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato

fun AfiliadoARegistrarModel.traerAfiliadoEntity() : AfiliadoEntity {
    return AfiliadoEntity(
        afiliadoId = null,
        nombres = this.nombres,
        apellidos = this.apellidos,
        tipoDocumento = this.tipoDocumento?.tipoDocumento?.traerId(),
        documento = this.numeroDocumento,
        fechaNacimiento = this.fechaNacimiento?.convertirAFormato(formato = FormatosFecha.ISO_8610),
        contrasenia = this.contrasenia
    )
}

fun AfiliadoARegistrarModel.traerCorreoEntity() : CorreosEntity {
    return CorreosEntity(
        registro = null,
        correo = this.correo,
        fechaRegistro = this.fechaInscripcion?.convertirAFormato(FormatosFecha.ISO_8610)
    )
}

fun AfiliadoARegistrarModel.traerDireccionEntity() : DireccionesEntity {
    return DireccionesEntity(
        direccionId = null,
        direccion = this.direccion
    )
}

fun AfiliadoARegistrarModel.traerTelefonoEntity() : TelefonosEntity {
    return TelefonosEntity(
        telefonoId = null,
        telefono = this.telefono,
        tipoTelefonoId = this.tipoTelefono!!.tipoTelefono.traerId()
    )
}