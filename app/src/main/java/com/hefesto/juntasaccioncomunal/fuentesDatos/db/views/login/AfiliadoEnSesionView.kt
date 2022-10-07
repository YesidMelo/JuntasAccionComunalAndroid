package com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.login

import androidx.room.DatabaseView

@DatabaseView(
    "SELECT " +
    "af.afiliadoId, af.nombres , af.apellidos, co.correo " +
    "FROM AfiliadoEntity af, Afiliado_Correo_Entity ac, CorreosEntity co " +
    "WHERE " +
    "ac.correoId = co.registro AND " +
    "af.afiliadoId = ac.afiliadoId " +
    "ORDER BY " +
    "co.fechaRegistro " +
    "LIMIT 1"
)
data class AfiliadoEnSesionView (
    var afiliadoId: Int,
    var nombres: String,
    var apellidos: String,
    var correo: String
)