package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ConvocantesEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.PuntosReunionEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ReunionAsambleaEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.ConvocanteReunionAsambleaAAgendarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.DetalleReunionAAgendarModel
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.PuntoReunionAgendarReunionAsambleaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirADate
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import java.util.*

//region elemento simple
fun DetalleReunionAAgendarModel.convertirAReunionAsambleaEntity(): ReunionAsambleaEntity {
    val fecha = this.fechaReunion!!.convertirAFormato(formato = FormatosFecha.ANIO_MES_DIA_GIONES)
    val horaMinuto = this.horaReunion!!.convertirAFormato(formato = FormatosFecha.HORA_MINUTO_24H)
    val fechaARegistra = "${fecha}T${horaMinuto}:00"
    return ReunionAsambleaEntity(
        asuntoReunion = this.tituloReunion,
        tipoReunionId = this.tipoReunion?.traerId(),
        fechaRegistro = Date().convertirAFormato(formato = FormatosFecha.ISO_8610),
        fechaYHoraProgramacionReunion = fechaARegistra.convertirADate(formatoEntrada = FormatosFecha.ISO_8610)?.convertirAFormato(FormatosFecha.ISO_8610),
        jacId = this.jacId,
        sitioReunion = this.sitioReunion
    )
}

fun PuntoReunionAgendarReunionAsambleaModel.convertirAPuntoReunionEntity() : PuntosReunionEntity {
    return PuntosReunionEntity(
        tituloPunto = this.tituloPunto
    )
}


fun DetalleReunionAAgendarModel.traerListaConvocantesEntity() : List<ConvocantesEntity> {
    val lista = emptyList<ConvocantesEntity>().toMutableList()
    this.listaConvocantes.forEach {
        lista.add(ConvocantesEntity(
            reunionId = this.reunionAsambleaId,
            afiliadoId = it.afiliadoId
        ))
    }
    return lista
}

//endregion

//region lista


fun DetalleReunionAAgendarModel.traerListaPuntosReunion() : List<PuntosReunionEntity> {
    val lista = this.puntosReunion.convertirAListaPuntoReunionEntity()
    lista.forEach {
        it.reunionId = this.reunionAsambleaId
    }
    return lista
}

fun List<PuntoReunionAgendarReunionAsambleaModel>.convertirAListaPuntoReunionEntity() : List<PuntosReunionEntity> {
    val lista = emptyList<PuntosReunionEntity>().toMutableList()
    forEach {
        lista.add(it.convertirAPuntoReunionEntity())
    }
    return lista
}

//endregion