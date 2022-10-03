package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

enum class TipoDocumento constructor(private val id: Int) {
    REGISTRO_CIVIL(id = 1),
    TARJETA_IDENTIDAD(id = 2),
    CEDULA_CIUDADANIA(id = 3),
    CEDULA_EXTRANJERIA(id = 4),
    ;
}