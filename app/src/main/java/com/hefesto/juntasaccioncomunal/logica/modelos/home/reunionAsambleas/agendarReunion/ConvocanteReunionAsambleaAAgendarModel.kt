package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class ConvocanteReunionAsambleaAAgendarModel (
    val afiliadoId: Int? = null,
    var nombres: String?= null,
    var apellidos: String? = null,
    var numeroDocumento: String? = null
) : BaseModel()