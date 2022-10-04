package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CorreosAfiliadoEntity (
    @PrimaryKey(autoGenerate = true)
    var registro : Int? = null,
    var correo: String? = null,
    var fechaRegistro: String? = null
)