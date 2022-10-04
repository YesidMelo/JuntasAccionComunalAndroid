package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.general

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

/**
 * lista de los tipos de documento
 */

@Entity
data class TipoDocumentoEntity (
    @PrimaryKey(autoGenerate = true)
    var tipoDocumentoId: Int? = null,
    var nombre: String? = null
) : BaseEntity()