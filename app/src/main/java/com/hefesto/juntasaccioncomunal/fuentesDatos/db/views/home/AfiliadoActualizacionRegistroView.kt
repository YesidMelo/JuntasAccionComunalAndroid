package com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home

import androidx.room.DatabaseView
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoDocumento
import java.util.*

@DatabaseView(
    "SELECT  " +
    "    a.*, " +
    "    ajea.jacId, " +
    "    ajea.rolEnLaAppId, " +
    "    ajea.estadoAfiliacionId, " +
    "    ajea.fechaActualizacion " +
    "FROM  " +
    "   AfiliadoEntity a,  " +
    "   Afiliado_Jac_EstadoAfiliacionEntity ajea " +
    "WHERE " +
    "   a.afiliadoId = ajea.afiliadoId " +
    "ORDER BY " +
    "   ajea.fechaActualizacion " +
    "DESC " +
    "LIMIT 1"
)
data class AfiliadoActualizacionRegistroView(
    var afiliadoId: Int? = null,
    var nombres: String? = null,
    var apellidos: String? = null,
    var fechaNacimiento: String? = null,
    var tipoDocumento: Int? = null,
    var documento: String? = null,
    var jacId : Int? = null,
    var rolEnLaAppId : Int? = null,
    var credencialesSesion: Int? = null,
    var estadoAfiliacionId : Int? = null,
    var fechaActualizacion : String? = null
)