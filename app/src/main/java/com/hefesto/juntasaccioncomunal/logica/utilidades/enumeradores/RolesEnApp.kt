package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

enum class RolesEnApp constructor(private val id: Int) {
    ADMINISTRADOR(id = 1),
    JAC(id = 2),
    PRESIDENTE(id = 3),
    VICEPRESIDENTE(id = 4),
    SECRETARIO(id = 5),
    FISCAL(id = 6),
    TESORERO(id = 7),
    AFILIADO(id = 8),
    ;

    fun traerId() = id
}