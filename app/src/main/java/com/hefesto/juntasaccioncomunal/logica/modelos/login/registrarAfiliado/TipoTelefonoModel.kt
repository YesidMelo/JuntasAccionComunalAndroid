package com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado

import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoTelefono

class TipoTelefonoModel (
    val tipoTelefono: TipoTelefono,
    val nombre: String
) {

    override fun toString(): String = nombre
}