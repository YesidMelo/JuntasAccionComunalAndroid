package com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoTelefono

data class ContactoParaRegistrarModel (
    var correoElectronicoId: Int? = null,
    var correo: String? = null,
    var direccionId: Int? = null,
    var direccion: String? = null,
    var tipoTelefono: TipoTelefono? = null,
    var telefonoId: Int?= null,
    var telefono: String? = null,
): BaseModel()