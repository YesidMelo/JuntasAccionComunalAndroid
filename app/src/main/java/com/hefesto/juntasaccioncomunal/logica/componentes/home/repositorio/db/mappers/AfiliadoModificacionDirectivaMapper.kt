package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoModificacionDirectivaView
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoModificacionDirectivaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp

//region unico objeto
fun AfiliadoModificacionDirectivaView.convertirAfiliadoModificacionDirectivaModel() : AfiliadoModificacionDirectivaModel {
    return AfiliadoModificacionDirectivaModel(
        afiliadoId = this.afiliadoId,
        nombres = this.nombres,
        apellidos = this.apellidos,
        estadoAfiliacion = EstadoAfiliacion.traerEstadoAfiliacionPorId(id = this.estadoAfiliacionId!!),
        rolApp = RolesEnApp.traerRolAppPorId(id = this.rolEnLaAppId!!)
    )
}
//endregion

//region varios objetos
fun List<AfiliadoModificacionDirectivaView>.convertirAListaAfiliadoModificacionDirectivaModel(): List<AfiliadoModificacionDirectivaModel> {
    val lista = emptyList<AfiliadoModificacionDirectivaModel>().toMutableList()
    forEach { lista.add(it.convertirAfiliadoModificacionDirectivaModel()) }
    return lista
}
//endregion