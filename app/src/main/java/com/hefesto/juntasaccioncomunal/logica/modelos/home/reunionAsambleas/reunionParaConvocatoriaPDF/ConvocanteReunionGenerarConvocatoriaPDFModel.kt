package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class ConvocanteReunionGenerarConvocatoriaPDFModel (
    val afiliadoId: Int? = null,
    var nombres: String?= null,
    var apellidos: String? = null,
    var documento: String? = null
) : BaseModel()