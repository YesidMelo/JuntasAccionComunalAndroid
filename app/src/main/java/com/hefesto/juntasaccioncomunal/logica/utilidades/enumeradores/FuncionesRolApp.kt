package com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores

enum class FuncionesRolApp constructor(
    private val id: Int,
    private val nombre: String,
) {
    ;

    fun traerId() = id
    fun traerNombre() = nombre
}