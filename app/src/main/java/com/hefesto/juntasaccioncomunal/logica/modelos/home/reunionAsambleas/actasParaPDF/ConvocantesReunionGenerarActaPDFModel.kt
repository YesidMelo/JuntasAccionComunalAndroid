package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class ConvocantesReunionGenerarActaPDFModel (
    var afiliadoId: Int?,
    var nombres: String?= null,
    var apellidos: String? = null
) : BaseModel()