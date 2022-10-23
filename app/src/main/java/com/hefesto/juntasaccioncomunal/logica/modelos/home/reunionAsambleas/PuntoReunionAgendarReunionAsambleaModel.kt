package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class PuntoReunionAgendarReunionAsambleaModel(
    var tituloPunto: String? = null,
    var puntoReunionId: Int? = null
): BaseModel()