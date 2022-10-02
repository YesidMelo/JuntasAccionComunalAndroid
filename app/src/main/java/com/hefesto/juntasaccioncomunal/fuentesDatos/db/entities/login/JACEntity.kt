package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class JACEntity(
    @PrimaryKey(autoGenerate = true) var jacID: Int? = null,
    var nombreJAC: String?,
    var codigoJAC: String?,
    var correoJAC: String?,
    var contraseniaJAC: String?
) : BaseEntity()
