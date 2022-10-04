package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * tabla rompimiento entre afiliado entity y jacentity
 */
@Entity
data class TablaRompimientoAfiliadoJacEstadoAfiliacionEntity(
    @PrimaryKey(autoGenerate = true)
    var registro : Int? = null,
    var afiliadoId: Int? = null,
    var jacId : Int? = null,
    var estadoAfiliacionId: Int? = null,
    var fechaActualizacion : Int? = null,
    var observaciones: String? = null,
    var secretarioId: Int? = null
)