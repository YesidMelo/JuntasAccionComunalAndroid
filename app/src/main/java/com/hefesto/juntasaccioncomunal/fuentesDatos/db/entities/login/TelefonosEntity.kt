package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

/**
 * telefono del afiliado
 */
@Entity
data class TelefonosEntity (
    @PrimaryKey(autoGenerate = true)
    var telefonoId: Int? = null,
    var telefono: String? = null,
    var tipoTelefonoId: Int? = null
): BaseEntity()