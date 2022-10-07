package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

enum class RolesEnApp constructor(private val id: Int, private val nombre : String) {
    ADMINISTRADOR(id = 1, nombre = "Administracion"),
    JAC(id = 2, nombre = "JAC"),
    PRESIDENTE(id = 3, nombre = "Presidente"),
    VICEPRESIDENTE(id = 4, nombre = "Vicepresidente"),
    SECRETARIO(id = 5, nombre = "Secretario"),
    FISCAL(id = 6, nombre = "Fiscal"),
    TESORERO(id = 7, nombre = "Tesorero"),
    AFILIADO(id = 8, nombre = "Afiliado"),
    PREAFILIADO(id = 9, nombre = "pre-afiliado"),
    ;

    fun traerId() = id
    fun traerNombre() = nombre
}