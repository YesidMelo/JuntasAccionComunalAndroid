package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * telefono del afiliado
 */
@Entity
data class TelefonosEntity (
    @PrimaryKey(autoGenerate = true)
    var registro: Int? = null,
    var telefono: String? = null,
    var tipoTelefonoId: Int? = null
)