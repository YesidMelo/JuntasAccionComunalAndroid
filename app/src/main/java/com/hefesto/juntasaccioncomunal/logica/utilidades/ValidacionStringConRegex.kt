package com.hefesto.juntasaccioncomunal.logica.utilidades

import java.util.regex.Pattern

fun correoValido(correo: String) : Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()
}

fun validarConRegex(string : String, regex: RegexEnum) : Boolean {
    return Pattern.matches(regex.traerRegex(),string)
}

/*
fun contraseniaValida(contrasenia: String) : Boolean {
    return Pattern.matches(RegexEnum.CONSTRASENIA.traerRegex(),contrasenia)
}

fun validarNombre(nombre: String) : Boolean {
    return Pattern.matches(RegexEnum.NOMBRE_JAC.traerRegex(),nombre)
}

fun validar(nombre: String) : Boolean {
    return Pattern.matches(RegexEnum.NOMBRE_JAC.traerRegex(),nombre)
}
*/