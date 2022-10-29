package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class ReunionAsambleaEntity(
    @PrimaryKey(autoGenerate = true)
    var reunionAsambleaId: Int? = null,
    var asuntoReunion: String?= null,
    var tipoReunionId: Int?= null,
    var fechaRegistro: String?= null,
    var fechaYHoraProgramacionReunion: String?= null,
    var creoActa : Boolean = false,
    var horaInicio: String? = null,
    var horaFin:String? = null,
    var jacId: Int? = null,
    var sitioReunion: String?= null
) : BaseEntity()
