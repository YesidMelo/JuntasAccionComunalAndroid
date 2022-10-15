package com.hefesto.juntasaccioncomunal.logica.modelos.general

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.ComitesEnJAC

class ComiteEnJacModel constructor(
    var comitesEnJAC: ComitesEnJAC,
    private var nombre: String
) : BaseModel() {
    override fun toString(): String = nombre
}