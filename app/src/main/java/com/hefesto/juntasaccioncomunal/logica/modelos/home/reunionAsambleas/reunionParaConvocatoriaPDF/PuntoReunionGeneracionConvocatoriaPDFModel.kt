package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class PuntoReunionGeneracionConvocatoriaPDFModel constructor(
    var puntoReunionId: Int? = null,
    var reunionId: Int? = null,
    var tituloPunto: String? = null
) : BaseModel()