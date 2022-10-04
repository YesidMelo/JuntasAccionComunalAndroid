package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

enum class RegexEnum constructor(private val regex: String) {
    APELLIDO_AFILIADO("[a-zA-Z0-9\\s]{3,50}"),
    CODIGO_JAC("[0-9]{1,20}"),
    CONSTRASENIA("[a-zA-Z0-9@\$]{5,20}"),
    DIRECCION("[a-zA-Z0-9]{4,20}"),
    DOCUMENTO_IDENTIDAD("[a-zA-Z0-9]{4,20}"),
    NOMBRE_AFILIADO("[a-zA-Z0-9\\s]{3,50}"),
    NOMBRE_JAC("[a-zA-Z0-9\\s]{5,20}"),
    TELEFONO_FIJO("[0-9]{7}"),
    TELEFONO_MOVIL("[0-9]{10}"),
    ;
    fun traerRegex() = regex
}