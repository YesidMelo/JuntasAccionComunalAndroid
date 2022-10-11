package com.hefesto.juntasaccioncomunal.logica.modelos.home

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel

data class DatosContactoAfiliadoModel(
    var direccionId: Int? = null,
    var direccion: String? = null,
    var correoId: Int? = null,
    var correo: String? = null,
    var telefonoId: Int? = null,
    var telefono: String? = null,
) : BaseModel()
