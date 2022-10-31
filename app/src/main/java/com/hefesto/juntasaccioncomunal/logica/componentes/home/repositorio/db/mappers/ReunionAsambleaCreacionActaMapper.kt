package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.PuntosReunionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ReunionAsambleaEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.PuntoReunionParaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirADate
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato

//region unico elemento

fun ReunionAsambleaEntity.convertirAReunionAsambleaCreacionActaModel(): ReunionAsambleaCreacionActaModel {
    return ReunionAsambleaCreacionActaModel(
        reunionAsambleaId = this.reunionAsambleaId,
        asuntoReunion = this.asuntoReunion,
        tipoReunion = TipoReunion.buscarTipoReunionPorId(this.tipoReunionId),
        fechaRegistro = this.fechaRegistro?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610),
        fechaYHoraProgramacionReunion = this.fechaYHoraProgramacionReunion?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610),
        creoActa = this.creoActa,
        jacId = this.jacId
    )
}

fun PuntosReunionEntity.convertirAPuntoReunionParaCreacionActaModel() : PuntoReunionParaCreacionActaModel {
    return PuntoReunionParaCreacionActaModel(
        puntoReunionId = this.puntoReunionId,
        reunionId = this.reunionId,
        tituloPunto = this.tituloPunto,
        detallePunto = this.detallePunto,
        votosAFavor =  this.votosAFavor,
        votosEnContra = this.votosEnContra,
        tieneVotacion = this.tieneVotacion
    )
}

fun PuntoReunionParaCreacionActaModel.convertirAPuntosReunionEntity() : PuntosReunionEntity {
    return PuntosReunionEntity(
        puntoReunionId = this.puntoReunionId,
        reunionId = this.reunionId,
        tituloPunto = this.tituloPunto,
        detallePunto = this.detallePunto,
        votosAFavor = this.votosAFavor,
        votosEnContra = this.votosEnContra,
        tieneVotacion = this.tieneVotacion
    )
}

fun ReunionAsambleaCreacionActaModel.convertirAReunionAsambleaEntity(): ReunionAsambleaEntity {
    return ReunionAsambleaEntity(
        reunionAsambleaId = this.reunionAsambleaId,
        asuntoReunion = this.asuntoReunion,
        tipoReunionId = this.tipoReunion?.traerId(),
        fechaYHoraProgramacionReunion = this.fechaYHoraProgramacionReunion?.convertirAFormato(formato = FormatosFecha.ISO_8610),
        fechaRegistro = this.fechaRegistro?.convertirAFormato(formato = FormatosFecha.ISO_8610),
        horaFin = this.horaFin?.convertirAFormato(formato = FormatosFecha.ISO_8610),
        horaInicio = this.horaInicio?.convertirAFormato(formato = FormatosFecha.ISO_8610),
        jacId = this.jacId
    )
}

//endregion

//region lista elementos

fun List<ReunionAsambleaEntity>.convertirAListaReunionAsambleaCreacionActaModel() : List<ReunionAsambleaCreacionActaModel> {
    val lista = emptyList<ReunionAsambleaCreacionActaModel>().toMutableList()
    forEach { lista.add(it.convertirAReunionAsambleaCreacionActaModel()) }
    return lista
}

fun List<PuntosReunionEntity>.convertirAPuntoReunionParaCreacionActaModel() : List<PuntoReunionParaCreacionActaModel> {
    val lista = emptyList<PuntoReunionParaCreacionActaModel>().toMutableList()
    forEach { lista.add(it.convertirAPuntoReunionParaCreacionActaModel()) }
    return lista
}

fun List<PuntoReunionParaCreacionActaModel>.convertirAListaPuntosReunionEntity() : List<PuntosReunionEntity> {
    val lista = emptyList<PuntosReunionEntity>().toMutableList()
    forEach { lista.add(it.convertirAPuntosReunionEntity()) }
    return lista
}

//endregion