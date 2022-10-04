package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class RolAfiliacionEntity(
    @PrimaryKey(autoGenerate = true)
    var rolAfiliacionId: Int? = null,
    var nombre: String? = null
) : BaseEntity()
