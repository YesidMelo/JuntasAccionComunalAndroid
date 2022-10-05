package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class DireccionesEntity (
    @PrimaryKey(autoGenerate = true)
    var direccionId : Int? = null,
    var direccion: String? = null,
) : BaseEntity()