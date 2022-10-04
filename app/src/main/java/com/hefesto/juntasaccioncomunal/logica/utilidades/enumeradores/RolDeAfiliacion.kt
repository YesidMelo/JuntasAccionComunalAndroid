package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

enum class RolDeAfiliacion (private val id: Int) {
    DUENIO_CASA(id = 1),
    DUENIO_NEGOCIO(id = 2),
    HIJO_DUENIO_CASA(id = 3),
    ARRENDATARIO(id = 4),
    HIJO_ARRENDATARIO(id = 5),
    ;

    fun traerId() = id
}