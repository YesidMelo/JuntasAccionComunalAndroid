package com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.modelos.general.TipoDocumentoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.general.TipoTelefonoModel
import java.util.Date

data class AfiliadoARegistrarModel(
    var apellidos: String? = null,
    var jacSeleccionado: JACDisponibleParaAfiliadoModel? = null,
    var contrasenia: String? = null,
    var correo: String? = null,
    var direccion: String? = null,
    var fechaInscripcion: Date? = null,
    var fechaNacimiento: Date? = null,
    var nombres : String? = null,
    var numeroDocumento: String? = null,
    var repetirContrasenia: String? = null,
    var telefono: String? = null,
    var tipoDocumento: TipoDocumentoModel? = null,
    var tipoTelefono: TipoTelefonoModel? = null,
) : BaseModel()