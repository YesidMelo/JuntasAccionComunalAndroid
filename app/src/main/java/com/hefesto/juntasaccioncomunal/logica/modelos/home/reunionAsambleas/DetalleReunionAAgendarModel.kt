package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import java.util.*

data class DetalleReunionAAgendarModel (
    var reunionAsambleaId: Int? = null,
    var tituloReunion: String?= null,
    var tipoReunion: TipoReunion?= null,
    var fechaReunion: Date? = null,
    var horaReunion: Date?= null,
    var puntosReunion: List<PuntoReunionAgendarReunionAsambleaModel>
): BaseModel()