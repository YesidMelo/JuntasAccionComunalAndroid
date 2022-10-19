package com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home

import androidx.room.DatabaseView

@DatabaseView(
    "SELECT  " +
    "ajce.afiliadoid, " +
    "ajce.jacid, " +
    "ajce.comiteid, " +
    "ajce.fechaactualizacion as fechaActualizacionComite, " +
    "ajeae.rolenlaappid, " +
    "ajeae.estadoafiliacionid, " +
    "ajeae.fechaactualizacion aS fechaActualizacionEstadoAfiliado, " +
    "ajeae.observaciones, " +
    "ajeae.secretarioid " +
    "from  " +
    "(SELECT * from Afiliado_Jac_Comite_Entity order by fechaactualizacion DESC) ajce, " +
    "(SELECT * from (SELECT  *  from Afiliado_Jac_EstadoAfiliacionEntity order by fechaactualizacion DESC) ajeae group BY ajeae.afiliadoid, ajeae.jacid) ajeae " +
    "where " +
    "ajce.afiliadoid = ajeae.afiliadoid and " +
    "ajce.jacid = ajeae.jacid"
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
