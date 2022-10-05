package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class CorreosEntity (
    @PrimaryKey(autoGenerate = true)
    var registro : Int? = null,
    var correo: String? = null,
    var fechaRegistro: String? = null
) : BaseEntity()