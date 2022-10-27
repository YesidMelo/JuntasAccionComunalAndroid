package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.PuntosReunionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ReunionAsambleaEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.PuntoReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.actasParaPDF.ReunionParaGenerarPDFModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirADate

//region unico elemento
fun ReunionAsambleaEntity.convertirAReunionParaGenerarPDFModel() : ReunionParaGenerarPDFModel {
    return ReunionParaGenerarPDFModel(
        reunionAsambleaId = this.reunionAsambleaId,
        asuntoReunion = this.asuntoReunion,
        tipoReunion = TipoReunion.buscarTipoReunionPorId(id = this.tipoReunionId),
        fechaRegistro = this.fechaRegistro?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610),
        fechaYHoraProgramacionReunion = this.fechaYHoraProgramacionReunion?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610),
        creoActa = this.creoActa,
        horaInicio = this.horaInicio?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610),
        horaFin = this.horaFin?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610)
    )
}

fun PuntosReunionEntity.convertirAPuntoReunionParaGenerarPDFModel() : PuntoReunionParaGenerarPDFModel {
    return PuntoReunionParaGenerarPDFModel(
        puntoReunionId = this.puntoReunionId,
        reunionId = this.reunionId,
        tituloPunto = this.tituloPunto,
        detallePunto = this.detallePunto,
        votosAFavor = this.votosAFavor,
        votosEnContra = this.votosEnContra
    )
}

//endregion

//region lista

fun List<ReunionAsambleaEntity>.convertirAListaReunionParaGenerarPDFModel(): List<ReunionParaGenerarPDFModel> {
    val lista = emptyList<ReunionParaGenerarPDFModel>().toMutableList()
    forEach { lista.add(it.convertirAReunionParaGenerarPDFModel()) }
    return lista
}

fun List<PuntosReunionEntity>.convertirAListaPuntoReunionParaGenerarPDFModel(): List<PuntoReunionParaGenerarPDFModel> {
    val lista = emptyList<PuntoReunionParaGenerarPDFModel>().toMutableList()
    forEach { lista.add(it.convertirAPuntoReunionParaGenerarPDFModel()) }
    return lista
}
//endregion