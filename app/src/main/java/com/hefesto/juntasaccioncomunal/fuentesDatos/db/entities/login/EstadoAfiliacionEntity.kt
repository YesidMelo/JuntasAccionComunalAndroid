package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class EstadoAfiliacionEntity (
    @PrimaryKey(autoGenerate = true)
    var estadoAfiliadoId: Int? = null,
    var nombre: String? = null
) : BaseEntity()