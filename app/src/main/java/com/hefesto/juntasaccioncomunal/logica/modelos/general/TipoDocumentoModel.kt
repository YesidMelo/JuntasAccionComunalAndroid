package com.hefesto.juntasaccioncomunal.logica.modelos.general

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento

class TipoDocumentoModel constructor(
    val tipoDocumento: TipoDocumento,
    val nombre: String
) : BaseModel() {

    override fun toString(): String = nombre
}