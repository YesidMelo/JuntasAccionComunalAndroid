package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.reunionAsamblea.ListaAsistenciaEntity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoAsistenciaView
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.crearActa.AfiliadoActaAsistenciaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import java.util.*

//region unico elemento

fun AfiliadoAsistenciaView.convertirAAfiliadoAsistenciaModel() : AfiliadoActaAsistenciaModel {
    return AfiliadoActaAsistenciaModel(
        afiliadoId = this.afiliadoId,
        nombres = this.nombres,
        apellidos = this.apellidos,
        numeroDocumento = this.documento,
        asistioAReunion = false
    )
}

fun AfiliadoActaAsistenciaModel.convertirAListaAsistenciaEntity(reunionId: Int) : ListaAsistenciaEntity {
    return ListaAsistenciaEntity(
        afiliadoId = this.afiliadoId,
        fechaRegistro = Date().convertirAFormato(formato = FormatosFecha.ISO_8610),
        reunionAsistenciaId = reunionId
    )
}

//endregion

//region lista elementos
fun List<AfiliadoAsistenciaView>.convertirAListaAfiliadoAsistenciaModel() : List<AfiliadoActaAsistenciaModel> {
    val lista = emptyList<AfiliadoActaAsistenciaModel>().toMutableList()
    forEach { lista.add(it.convertirAAfiliadoAsistenciaModel()) }
    return lista
}

fun List<AfiliadoActaAsistenciaModel>.convertirAListaAsistenciaEntity(reunionId: Int): List<ListaAsistenciaEntity> {
    val lista = emptyList<ListaAsistenciaEntity>().toMutableList()
    forEach { lista.add(it.convertirAListaAsistenciaEntity(reunionId = reunionId)) }
    return lista
}
//endregion