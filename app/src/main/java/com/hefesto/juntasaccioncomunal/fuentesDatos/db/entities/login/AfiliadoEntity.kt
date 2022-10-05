package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class AfiliadoEntity(
    @PrimaryKey(autoGenerate = true)
    val afiliadoId: Int? = null,
    val nombres: String? = null,
    val apellidos: String? = null,
    val tipoDocumento: Int? = null,
    val documento: String? = null,
    val fechaNacimiento: String? = null,
    val contrasenia: String? = null
): BaseEntity()
