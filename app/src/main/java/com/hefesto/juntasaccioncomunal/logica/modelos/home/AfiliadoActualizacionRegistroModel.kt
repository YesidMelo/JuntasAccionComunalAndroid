package com.hefesto.juntasaccioncomunal.logica.modelos.home

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.ComitesEnJAC
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoTelefono
import java.util.*

data class AfiliadoActualizacionRegistroModel (
    var afiliadoId : Int? = null,
    var jacId: Int? = null,
    var nombres: String? = null,
    var apellidos: String? = null,
    var fechaNacimiento: Date? = null,
    var tipoDocumento: TipoDocumento? = null,
    var documento: String? = null,
    var correo: String? = null,
    var direccion: String? = null,
    var tipoTelefono: TipoTelefono? = null,
    var telefono: String? = null,
    var contrasenia: String? = null,
    var repetirContrasenia: String? = null,
    var estadoAfiliacion: EstadoAfiliacion? = null,
    var rolAfiliacion: RolesEnApp? = null,
    var comite: ComitesEnJAC? = null
) :BaseModel()