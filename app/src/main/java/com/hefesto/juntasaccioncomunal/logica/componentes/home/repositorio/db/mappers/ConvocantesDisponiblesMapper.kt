package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.ConvocantesDisponiblesView
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.ConvocanteReunionAsambleaAAgendarModel

//region elemento
fun ConvocantesDisponiblesView.convertirAConvocanteReunionAsambleaAAgendarModel(): ConvocanteReunionAsambleaAAgendarModel {
    return ConvocanteReunionAsambleaAAgendarModel(
        afiliadoId = this.afiliadoId,
        nombres = this.nombres,
        apellidos = this.apellidos,
        numeroDocumento = this.documento
    )
}
//endregion

//region lista
fun List<ConvocantesDisponiblesView>.convertirAListConvocanteReunionAsambleaAAgendarModel(): List<ConvocanteReunionAsambleaAAgendarModel> {
    val lista = emptyList<ConvocanteReunionAsambleaAAgendarModel>().toMutableList()
    forEach { lista.add(it.convertirAConvocanteReunionAsambleaAAgendarModel()) }
    return lista
}
//endregion