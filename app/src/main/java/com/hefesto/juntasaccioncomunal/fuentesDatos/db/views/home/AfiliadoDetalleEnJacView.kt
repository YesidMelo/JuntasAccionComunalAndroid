package com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home

import androidx.room.DatabaseView

@DatabaseView(
    "SELECT  " +
    " ajeae.afiliadoId, " +
    " ajeae.jacid, " +
    " ajeae.rolenlaappid, " +
    " ajeae.estadoafiliacionid, " +
    " ajeae.fechaactualizacion as fechaActualizacionEstadoAfiliado, " +
    " ajeae.observaciones, " +
    " ajeae.secretarioid, " +
    " ajce.comiteid, " +
    " ajce.fechaactualizacion as fechaActualizacionComite " +
    "from " +
    " (SELECT ajce.* from Afiliado_Jac_Comite_Entity ajce order by fechaactualizacion DESC LIMIT 1) ajce, " +
    " (SELECT * from Afiliado_Jac_EstadoAfiliacionEntity ajeae order by ajeae.fechaactualizacion DESC LIMIT 1) ajeae " +
    "where " +
    " ajce.jacId = ajeae.jacId AND " +
    " ajce.afiliadoId = ajeae.afiliadoid " +
    "limit 1"
)
data class AfiliadoDetalleEnJacView(
    var afiliadoId : Int? = null,
    var jacId : Int? = null,
    var rolEnLaAppId : Int? = null,
    var estadoAfiliacionId : Int? = null,
    var fechaActualizacionEstadoAfiliado : String? = null,
    var observaciones : String? = null,
    var secretarioid : Int? = null,
    var comiteId : Int? = null,
    var fechaActualizacionComite : String? = null,
)
