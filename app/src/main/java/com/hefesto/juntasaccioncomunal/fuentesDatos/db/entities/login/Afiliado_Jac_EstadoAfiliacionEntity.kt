package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

/**
 * tabla rompimiento entre afiliado entity, jac entity, estado afiliacion entity, rolapp entity,
 */
@Entity
data class Afiliado_Jac_EstadoAfiliacionEntity(
    @PrimaryKey(autoGenerate = true)
    var registro : Int? = null,
    var afiliadoId: Int? = null,
    var jacId : Int? = null,
    var rolEnLaAppId: Int? = null,
    var estadoAfiliacionId: Int? = null,
    var fechaActualizacion : String? = null,
    var observaciones: String? = null,
    var secretarioId: Int? = null
) :BaseEntity()