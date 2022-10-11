package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class Afiliado_Jac_Comite_Entity(
    @PrimaryKey(autoGenerate = true)
    var registro: Int? = null,
    var jacId: Int? = null,
    var afiliadoId: Int? = null,
    var comiteId: Int? = null,
    var fechaActualizacion: String? = null
) : BaseEntity()