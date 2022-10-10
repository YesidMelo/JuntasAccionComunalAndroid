package com.hefesto.juntasaccioncomunal.logica.modelos.home

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp

data class AfiliadoEnDirectivaModificadoModel (
    var afiliadoId : Int,
    var estadoAfiliacion: EstadoAfiliacion,
    var rolApp: RolesEnApp
) : BaseModel()