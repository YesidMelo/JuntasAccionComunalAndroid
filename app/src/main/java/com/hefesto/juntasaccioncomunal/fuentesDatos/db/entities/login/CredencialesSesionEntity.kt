package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class CredencialesSesionEntity(
    @PrimaryKey(autoGenerate = true)
    var registro : Int? = null,
    var correoId: Int? = null,
    var contrasenia: String? = null
) : BaseEntity()