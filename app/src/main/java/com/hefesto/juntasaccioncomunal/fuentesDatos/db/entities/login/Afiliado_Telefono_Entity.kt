package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class Afiliado_Telefono_Entity (
    @PrimaryKey(autoGenerate = true)
    var registroID: Int? = null,
    var afiliadoId: Int? = null,
    var telefonoId: Int? = null,
    var observaciones: String? = null
): BaseEntity()