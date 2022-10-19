package com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento
import java.util.*

data class DatosBasicosParaRegistrarModel(
    var afiliadoId: Int? = null,
    var nombres: String? = null,
    var apellidos: String? = null,
    var fechaNacimiento: Date? = null,
    var tipoDocumento: TipoDocumento? = null,
    var numeroDocumento: String? = null,
    var credencialesSesion: Int? = null
) : BaseModel()