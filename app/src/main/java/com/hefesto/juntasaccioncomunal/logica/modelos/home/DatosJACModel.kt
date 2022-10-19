package com.hefesto.juntasaccioncomunal.logica.modelos.home

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.ComitesEnJAC
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolDeAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp
import java.util.*

data class DatosJACModel(
    var comite: ComitesEnJAC? = null,
    var fechaActualizacionComite: Date? = null,
    var estadoAfiliacion: EstadoAfiliacion? = null,
    var fechaActualizacionEstadoAfiliacion: Date? = null,
    var rolesEnApp: RolesEnApp? = null,
    var rolDeAfiliacion: RolDeAfiliacion? = null,
): BaseModel()
