package com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home

import androidx.room.DatabaseView

@DatabaseView(
    "SELECT * from  " +
        "(SELECT  " +
        "   afe.afiliadoId, " +
        "   afe.nombres, " +
        "   afe.apellidos, " +
        "   afe.documento, " +
        "   ajea.jacId " +
        "from  " +
        "   AfiliadoEntity afe, " +
        "   Afiliado_Jac_EstadoAfiliacionEntity ajea " +
        "WHERE " +
        "   afe.afiliadoId = ajea.afiliadoId " +
        "ORDER by " +
        "   ajea.fechaActualizacion DESC) tabla " +
    "GROUP by  " +
    " tabla.afiliadoid")
data class ConvocantesDisponiblesView(
    var afiliadoId : Int? = null,
    var jacId : Int? = null,
    var nombres : String? = null,
    var apellidos: String? = null,
    var documento: String? = null
)
