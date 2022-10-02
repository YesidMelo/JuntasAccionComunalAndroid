package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.JACEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarJAC.JACRegistroModel

fun JACRegistroModel.convertirAJACEntity() : JACEntity {
    return JACEntity(
        jacID = this.jacId,
        nombreJAC = this.NombreJAC,
        codigoJAC = this.CodigoJAC,
        correoJAC = this.Correo,
        contraseniaJAC = this.Contrasenia
    )
}

fun JACEntity.convertirAJACRegistroModel() : JACRegistroModel {
    return JACRegistroModel(
        jacId = this.jacID,
        NombreJAC = this.nombreJAC,
        CodigoJAC = this.codigoJAC,
        Correo = this.correoJAC,
        Contrasenia = this.contraseniaJAC
    )
}