package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class PuntosReunionEntity(
    @PrimaryKey(autoGenerate = true)
    var puntoReunionId: Int? = null,
    var reunionId: Int? = null,
    var tituloPunto: Int? = null,
    var detallePunto: Int? = null,
    var votosAFavor: Int? = null,
    var votosEnContra: Int? = null
) : BaseEntity()