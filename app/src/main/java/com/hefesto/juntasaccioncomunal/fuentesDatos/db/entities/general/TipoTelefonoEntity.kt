package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

/**
 * lista de los tipos de telefono
 */
@Entity
data class TipoTelefonoEntity(
    @PrimaryKey
    var tipoTelefonoId: Int? = null,
    var nombre: String? = null
) : BaseEntity()
