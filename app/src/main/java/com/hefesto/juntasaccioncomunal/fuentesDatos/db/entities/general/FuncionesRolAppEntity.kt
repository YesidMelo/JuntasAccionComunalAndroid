package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class FuncionesRolAppEntity(
    @PrimaryKey(autoGenerate = true)
    var funcionId : Int? = null,
    var nombre: String? = null
) : BaseEntity()
