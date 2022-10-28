package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class ConvocanteReunionAsambleaModel (
    val afiliadoId: Int? = null,
    var nombres: String?= null,
    var apellidos: String? = null,
    var numeroDocumento: String? = null
) : BaseModel()