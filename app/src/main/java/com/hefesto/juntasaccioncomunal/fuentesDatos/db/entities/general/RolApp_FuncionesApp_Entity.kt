package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class RolApp_FuncionesApp_Entity (
    @PrimaryKey(autoGenerate = true)
    var registro : Int? = null,
    var rolAppId: Int? = null,
    var funcionAppId: Int? = null
) : BaseEntity()