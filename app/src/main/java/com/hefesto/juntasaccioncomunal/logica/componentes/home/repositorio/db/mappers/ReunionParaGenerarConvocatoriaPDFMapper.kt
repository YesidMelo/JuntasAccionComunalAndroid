package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.PuntosReunionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ReunionAsambleaEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.PuntoReunionGeneracionConvocatoriaPDFModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.reunionParaConvocatoriaPDF.ReunionParaGenerarConvocatoriaPDFModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirADate

//region elementos
fun ReunionAsambleaEntity.convertirAReunionParaGenerarConvocatoriaPDFModel(): ReunionParaGenerarConvocatoriaPDFModel {
    return ReunionParaGenerarConvocatoriaPDFModel(
        reunionAsambleaId = this.reunionAsambleaId,
        asuntoReunion = this.asuntoReunion,
        tipoReunion = TipoReunion.buscarTipoReunionPorId(id = this.tipoReunionId),
        fechaRegistro = this.fechaRegistro?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610),
        fechaYHoraProgramacionReunion = this.fechaYHoraProgramacionReunion?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610),
        jacId = this.jacId,
        sitio = this.sitioReunion
    )
}

fun PuntosReunionEntity.convetirAPuntoReunionGeneracionConvocatoriaPDFModel(): PuntoReunionGeneracionConvocatoriaPDFModel {
    return PuntoReunionGeneracionConvocatoriaPDFModel(
        puntoReunionId = this.puntoReunionId,
        reunionId = this.reunionId,
        tituloPunto = this.tituloPunto,
    )
}

//endregion

//region listas
fun List<ReunionAsambleaEntity>.convertirAListReunionParaGenerarConvocatoriaPDFModel() : List<ReunionParaGenerarConvocatoriaPDFModel> {
    val lista = emptyList<ReunionParaGenerarConvocatoriaPDFModel>().toMutableList()
    forEach{ lista.add(it.convertirAReunionParaGenerarConvocatoriaPDFModel()) }
    return lista
}

fun List<PuntosReunionEntity>.convertirAListaPuntoReunionGeneracionConvocatoriaPDFModel(): List<PuntoReunionGeneracionConvocatoriaPDFModel> {
    val lista = emptyList<PuntoReunionGeneracionConvocatoriaPDFModel>().toMutableList()
    forEach { lista.add(it.convetirAPuntoReunionGeneracionConvocatoriaPDFModel()) }
    return lista
}
//endregion