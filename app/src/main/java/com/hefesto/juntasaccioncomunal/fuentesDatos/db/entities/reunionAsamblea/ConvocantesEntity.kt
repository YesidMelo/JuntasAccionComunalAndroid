package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.BaseEntity

@Entity
data class ConvocantesEntity (
    @PrimaryKey(autoGenerate = true)
    var registroId: Int? = null,
    var reunionId: Int? = null,
    var afiliadoId: Int? = null
) : BaseEntity()