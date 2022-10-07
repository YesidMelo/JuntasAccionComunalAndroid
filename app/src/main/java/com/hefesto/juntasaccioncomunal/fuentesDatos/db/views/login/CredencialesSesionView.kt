package com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.login

import androidx.room.DatabaseView

@DatabaseView("SELECT cre.registro, co.correo, cre.contrasenia FROM CredencialesSesionEntity cre, CorreosEntity cO WHERE co.registro = cre.correoId")
data class CredencialesSesionView(
    val registro : Int?,
    val correo: String?,
    val contrasenia: String
)
