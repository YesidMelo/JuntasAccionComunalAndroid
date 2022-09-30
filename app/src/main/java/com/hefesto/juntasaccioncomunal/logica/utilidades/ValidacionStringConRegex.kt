package com.hefesto.juntasaccioncomunal.logica.utilidades

import java.util.regex.Pattern

fun correoValido(correo: String) : Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()
}

fun contraseniaValida(contrasenia: String) : Boolean {
    return Pattern.matches("[a-zA-Z0-9@\$]{5,20}",contrasenia)
}