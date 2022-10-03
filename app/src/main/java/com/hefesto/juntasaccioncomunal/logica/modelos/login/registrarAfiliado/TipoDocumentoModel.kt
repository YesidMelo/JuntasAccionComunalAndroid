package com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado

import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento

class TipoDocumentoModel constructor(
    val tipoDocumento: TipoDocumento,
    val nombre: String
) {

    override fun toString(): String = nombre
}