package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class DetalleJACActaPDFModel (
    var nombreJAc: String?= null,
    var nit: String?= null,
    var PJ: String? = null
) : BaseModel()