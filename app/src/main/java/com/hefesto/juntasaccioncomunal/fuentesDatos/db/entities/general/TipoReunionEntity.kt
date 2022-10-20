package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class TipoReunionEntity(
    @PrimaryKey(autoGenerate = true)
    var tipoReunionId: Int,
    var nombre: String
) : BaseEntity()
