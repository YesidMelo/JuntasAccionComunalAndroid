package com.hefesto.juntasaccioncomunal.logica.utilidades

import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RegexEnum
import java.util.regex.Pattern

fun correoValido(correo: String) : Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()
}

fun validarConRegex(string : String, regex: RegexEnum) : Boolean {
    return Pattern.matches(regex.traerRegex(),string)
}