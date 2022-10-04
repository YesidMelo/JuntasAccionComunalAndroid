package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TablaRompimientoDireccionesTerritorioJACAfiliadoEntity(
    @PrimaryKey(autoGenerate = true)
    var registroId: Int? = null,
    var jacId : Int? = null,
    var direccionId: Int? = null
)