package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class ConvocantesReunionGenerarPDFModel (
    var afiliadoId: Int?,
    var nombre: String?= null,
    var apellido: String? = null
) : BaseModel()