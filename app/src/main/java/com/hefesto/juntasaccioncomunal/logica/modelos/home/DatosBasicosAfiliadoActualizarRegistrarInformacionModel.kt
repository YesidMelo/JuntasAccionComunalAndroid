package com.hefesto.juntasaccioncomunal.logica.modelos.home

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.ComitesEnJAC
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoTelefono
import java.util.*

data class DatosBasicosAfiliadoActualizarRegistrarInformacionModel (
    var afiliadoId: Int? = null,
    var nombres: String? = null,
    var apellidos: String? = null,
    var fechaNacimiento: Date? = null,
    var tipoDocumento: TipoDocumento? = null,
    var documento: String? = null,
    var jacId : Int? = null,
    var credencialesSesion: Int? = null,
    var datosJACModel : DatosJACModel? = null,
    var datosContactoAfiliadoModel: DatosContactoAfiliadoModel? = null,
    var datosSeguridadAfiliadoModel: DatosSeguridadAfiliadoModel? = null
) :BaseModel()