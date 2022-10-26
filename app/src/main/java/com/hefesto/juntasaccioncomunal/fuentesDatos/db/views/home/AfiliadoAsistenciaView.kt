package com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home

import androidx.room.DatabaseView

@DatabaseView("SELECT  " +
        " af.afiliadoId,  " +
        " af.nombres,  " +
        " af.apellidos,  " +
        " af.documento,  " +
        " ajeae.jacId  " +
        "from  " +
        " AfiliadoEntity af, " +
        " Afiliado_Jac_EstadoAfiliacionEntity ajeae " +
        "WHERE  " +
        " ajeae.afiliadoId = af.afiliadoId AND " +
        " ajeae.estadoafiliacionid = 2 ")
data class AfiliadoAsistenciaView (
    var afiliadoId: Int? = null,
    var nombres: String? = null,
    var apellidos: String? = null,
    var documento: String? = null,
    var jacId: String? = null,
)