package com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home

import androidx.room.DatabaseView

@DatabaseView("SELECT af.afiliadoId, af.nombres,af.apellidos,aje.jacId,aje.rolEnLaAppId,aje.estadoAfiliacionId FROM Afiliado_Jac_EstadoAfiliacionEntity aje, AfiliadoEntity af WHERE af.afiliadoId = aje.afiliadoId;")
data class AfiliadoModificacionDirectivaView(
    var afiliadoId : Int,
    var nombres: String?,
    var apellidos : String?,
    var jacId : Int?,
    var estadoAfiliacionId: Int?,
    var rolEnLaAppId: Int?
)
