package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

enum class EstadoAfiliacion (private val id: Int) {
    PRE_AFILIADO(id = 1),
    ACTIVO(id = 2),
    INACTIVO(id = 3),
    DESAFILIADO(id = 4),
    EXPULSADO(id = 5),
    FALLECIDO(id = 6),
    ;
    fun traerId() = id
}