package com.hefesto.juntasaccioncomunal.logica.modelos.general

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoTelefono

class TipoTelefonoModel (
    val tipoTelefono: TipoTelefono,
    val nombre: String
) : BaseModel() {

    override fun toString(): String = nombre
}