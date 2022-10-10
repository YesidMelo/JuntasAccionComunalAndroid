package com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home

import androidx.room.DatabaseView

private const val script  = "SELECT  " +
        " *  " +
        "from (SELECT  " +
        "        af.afiliadoId,  " +
        "        af.nombres,  " +
        "        af.apellidos,  " +
        "        af.documento,  " +
        "        aje.jacId,  " +
        "        aje.rolEnLaAppId,  " +
        "        aje.estadoAfiliacionId, " +
        "        aje.fechaActualizacion " +
        "FROM  " +
        "     Afiliado_Jac_EstadoAfiliacionEntity aje, " +
        "     AfiliadoEntity af " +
        "WHERE  " +
        " af.afiliadoId = aje.afiliadoId " +
        "order BY " +
        " aje.fechaactualizacion DESC) tabla " +
        "GROUP by  " +
        " tabla.afiliadoId "

@DatabaseView(script)
data class AfiliadoModificacionDirectivaView(
    var afiliadoId : Int,
    var nombres: String?,
    var apellidos : String?,
    var documento : String?,
    var jacId : Int?,
    var estadoAfiliacionId: Int?,
    var rolEnLaAppId: Int?
)
