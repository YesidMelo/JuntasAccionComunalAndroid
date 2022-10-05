package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class Afiliado_Direccion_Entity (
    @PrimaryKey(autoGenerate = true)
    var registro: Int? = null,
    var afiliadoId: Int? = null,
    var direccionId: Int? = null
): BaseEntity()