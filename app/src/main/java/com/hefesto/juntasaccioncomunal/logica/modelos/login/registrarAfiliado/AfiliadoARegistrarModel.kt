package com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado

import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoTelefono
import java.util.Date

data class AfiliadoARegistrarModel(
    var apellidos: String? = null,
    var codigoJAC: String? = null,
    var contrasenia: String? = null,
    var correo: String? = null,
    var direccion: String? = null,
    var fechaInscripcion: Date? = null,
    var fechaNacimiento: Date? = null,
    var nombres : String? = null,
    var numeroDocumento: String? = null,
    var repetirContrasenia: String? = null,
    var telefono: String? = null,
    var tipoDocumento: TipoDocumento? = null,
    var tipoTelefono: TipoTelefono? = null,
)