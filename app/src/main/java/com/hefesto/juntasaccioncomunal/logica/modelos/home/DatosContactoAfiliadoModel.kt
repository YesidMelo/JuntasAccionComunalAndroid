package com.hefesto.juntasaccioncomunal.logica.modelos.home

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoTelefono
import java.util.*

data class DatosContactoAfiliadoModel(
    var correoElectronicoId: Int? = null,
    var correo: String? = null,
    var fechaRegistroCorreo: Date? = null,
    var direccionId: Int? = null,
    var direccion: String? = null,
    var afiliadoDireccionId: Int? = null,
    var tipoTelefono: TipoTelefono? = null,
    var telefonoId: Int?= null,
    var telefono: String? = null,
    var afiliadoTelefonoId: Int? = null
) : BaseModel()
