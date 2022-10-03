package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

enum class TipoTelefono constructor(private val id: Int) {
    FIJO(id = 1),
    MOVIL(id = 2),
    ;

    fun traerId() = id
}