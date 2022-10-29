package com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF

import com.hefesto.juntasaccioncomunal.logica.modelos.BaseModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import java.util.*

data class ReunionParaGenerarConvocatoriaPDFModel (
    var reunionAsambleaId: Int? = null,
    var asuntoReunion: String?= null,
    var tipoReunion: TipoReunion?= null,
    var fechaRegistro: Date?= null,
    var fechaYHoraProgramacionReunion: Date?= null,
    var listaPuntos: List<PuntoReunionGeneracionConvocatoriaPDFModel>? = null,
    var jacId: Int? = null,
    var listaConvocantes: List<ConvocanteReunionGenerarConvocatoriaPDFModel>?= null
) : BaseModel()