package com.hefesto.juntasaccioncomunal.logica.modelos.general

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion

class EstadoAfiliadoModel constructor(
    val estadoAfiliacion: EstadoAfiliacion,
    val nombre : String
) : BaseModel() {
    override fun toString(): String = nombre
}