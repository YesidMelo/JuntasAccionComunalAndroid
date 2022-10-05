package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class Jac_Afiliado_Direccion_Entity(
    @PrimaryKey(autoGenerate = true)
    var registroId: Int? = null,
    var jacId : Int? = null,
    var direccionId: Int? = null,
    var afiliadoId: Int? = null,
    var fechaInscripcion: String? = null,
    var observaciones: String? = null
): BaseEntity()