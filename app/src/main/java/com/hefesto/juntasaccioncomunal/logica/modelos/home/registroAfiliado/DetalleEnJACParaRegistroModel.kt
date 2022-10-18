package com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.ComitesEnJAC
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion

data class DetalleEnJACParaRegistroModel (
    var estadoAfiliacion: EstadoAfiliacion? = null,
    var comitesEnJAC: ComitesEnJAC? = null,
    var observacionEstadoAfiliacion: String? = null
) : BaseModel()