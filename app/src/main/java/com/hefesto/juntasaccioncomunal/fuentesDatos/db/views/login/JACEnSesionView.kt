package com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.login

import androidx.room.DatabaseView

@DatabaseView("SELECT * FROM " +
        "JACEntity jac, " +
        "CorreosEntity co, " +
        "CredencialesSesionEntity cre " +
        "WHERE  " +
        "jac.credencialesSesion = cre.correoId AND " +
        "cre.correoId = co.registro " +
        "ORDER BY co.fechaRegistro DESC LIMIT 1")
data class JACEnSesionView(
    var jacID: Int,
    var nombreJAC: String,
    var codigoJAC: String,
    var correo: String,
)
