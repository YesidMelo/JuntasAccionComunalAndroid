package com.hefesto.juntasaccioncomunal.logica.utilidades

enum class RegexEnum constructor(private val regex: String) {
    CONSTRASENIA("[a-zA-Z0-9@\$]{5,20}"),
    NOMBRE_JAC("[a-zA-Z0-9\\s]{5,20}"),
    CODIGO_JAC("[0-9]{1,20}"),
    ;
    fun traerRegex() = regex
}