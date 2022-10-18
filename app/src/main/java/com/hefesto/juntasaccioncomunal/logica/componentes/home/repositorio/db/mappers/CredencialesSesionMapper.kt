package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.CredencialesSesionEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosSeguridadAfiliadoModel

fun CredencialesSesionEntity.convertirADatosSeguridadAfiliadoModel() : DatosSeguridadAfiliadoModel {
    return DatosSeguridadAfiliadoModel(
        contraseniaId = this.registro,
        contrasenia = this.contrasenia
    )
}