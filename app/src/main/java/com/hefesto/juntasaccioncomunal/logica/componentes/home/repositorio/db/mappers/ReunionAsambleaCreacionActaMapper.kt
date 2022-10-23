package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.PuntosReunionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ReunionAsambleaEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.PuntoReunionParaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.ReunionAsambleaCreacionActaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.TipoReunion
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirADate

//region unico elemento

fun ReunionAsambleaEntity.convertirAReunionAsambleaCreacionActaModel(): ReunionAsambleaCreacionActaModel {
    return ReunionAsambleaCreacionActaModel(
        reunionAsambleaId = this.reunionAsambleaId,
        asuntoReunion = this.asuntoReunion,
        tipoReunion = TipoReunion.buscarTipoReunionPorId(this.tipoReunionId),
        fechaRegistro = this.fechaRegistro?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610),
        fechaYHoraProgramacionReunion = this.fechaYHoraProgramacionReunion?.convertirADate(formatoEntrada = FormatosFecha.ISO_8610),
        creoActa = this.creoActa
    )
}

fun PuntosReunionEntity.convertirAPuntoReunionParaCreacionActaModel() : PuntoReunionParaCreacionActaModel {
    return PuntoReunionParaCreacionActaModel(
        puntoReunionId = this.puntoReunionId,
        reunionId = this.puntoReunionId,
        tituloPunto = this.tituloPunto,
        detallePunto = this.detallePunto,
        votosAFavor =  this.votosAFavor,
        votosEnContra = this.votosEnContra
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

//endregion