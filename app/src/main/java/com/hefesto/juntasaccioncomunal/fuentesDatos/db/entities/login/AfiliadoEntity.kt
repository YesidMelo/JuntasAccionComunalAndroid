package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class AfiliadoEntity(
    @PrimaryKey(autoGenerate = true)
    var afiliadoId: Int? = null,
    var nombres: String? = null,
    var apellidos: String? = null,
    var tipoDocumento: Int? = null,
    var documento: String? = null,
    var fechaNacimiento: String? = null,
    var credencialesSesion: Int? = null
): BaseEntity()
