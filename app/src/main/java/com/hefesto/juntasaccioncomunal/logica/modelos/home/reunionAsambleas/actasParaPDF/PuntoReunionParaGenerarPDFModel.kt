package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class PuntoReunionParaGenerarPDFModel constructor(
    var puntoReunionId: Int? = null,
    var reunionId: Int? = null,
    var tituloPunto: String? = null,
    var detallePunto: String? = null,
    var votosAFavor: Int? = null,
    var votosEnContra: Int? = null
) : BaseModel()