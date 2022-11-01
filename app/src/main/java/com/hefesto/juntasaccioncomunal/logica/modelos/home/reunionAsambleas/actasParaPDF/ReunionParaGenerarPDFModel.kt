package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import java.util.*

data class ReunionParaGenerarPDFModel constructor(
    var reunionAsambleaId: Int? = null,
    var asuntoReunion: String?= null,
    var tipoReunion: TipoReunion?= null,
    var fechaRegistro: Date?= null,
    var fechaYHoraProgramacionReunion: Date?= null,
    var creoActa : Boolean = false,
    var horaInicio: Date?= null,
    var horaFin: Date?= null,
    var listaPuntos: List<PuntoReunionParaGenerarPDFModel>? = null,
    var numeroActa: Int? = null,
    var sitio: String? = null,
    var marcaAgua: String? = null,
    var listaConvocantes: List<ConvocantesReunionGenerarActaPDFModel>? = null,
    //todo falta por traer desde db
    var numeroAsistentes :Int?= null,
    var numeroAfiliadosActivos: Int? = null,
    var presidente: ConvocantesReunionGenerarActaPDFModel? = null,
    var secretario: ConvocantesReunionGenerarActaPDFModel? = null,
): BaseModel()