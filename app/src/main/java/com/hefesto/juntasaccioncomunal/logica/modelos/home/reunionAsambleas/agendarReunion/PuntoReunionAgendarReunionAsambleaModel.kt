package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class PuntoReunionAgendarReunionAsambleaModel(
    var tituloPunto: String? = null,
    var puntoReunionId: Int? = null,
    var detallePunto: String?= null,
    var votosAFavor: Int? = null,
    var votosEnContra: Int? = null,
): BaseModel()