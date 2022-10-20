package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class ListaAsistenciaEntity(
    @PrimaryKey(autoGenerate = true)
    var registro : Int? = null,
    var reunionAsistenciaId: Int? = null,
    var afiliadoId: Int? = null,
    var fechaRegistro: String? = null
) : BaseEntity()
