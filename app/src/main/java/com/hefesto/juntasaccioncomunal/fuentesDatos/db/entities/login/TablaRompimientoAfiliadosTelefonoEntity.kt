package com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TablaRompimientoAfiliadosTelefonoEntity(
    @PrimaryKey(autoGenerate = true)
    var registroId: Int? = null,
    var telefonoId: Int? = null,
    var afiliadoId: Int? = null,
)
