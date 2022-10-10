package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.views.home.AfiliadoModificacionDirectivaView
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoParaModificacionDirectivaModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.RolesEnApp

//region unico objeto
fun AfiliadoModificacionDirectivaView.convertirAfiliadoModificacionDirectivaModel() : AfiliadoParaModificacionDirectivaModel {
    return AfiliadoParaModificacionDirectivaModel(
        afiliadoId = this.afiliadoId,
        nombres = this.nombres,
        apellidos = this.apellidos,
        documento = this.documento,
        estadoAfiliacion = EstadoAfiliacion.traerEstadoAfiliacionPorId(id = this.estadoAfiliacionId!!),
        rolApp = RolesEnApp.traerRolAppPorId(id = this.rolEnLaAppId!!)
    )
}
//endregion

//region varios objetos
fun List<AfiliadoModificacionDirectivaView>.convertirAListaAfiliadoModificacionDirectivaModel(): List<AfiliadoParaModificacionDirectivaModel> {
    val lista = emptyList<AfiliadoParaModificacionDirectivaModel>().toMutableList()
    forEach { lista.add(it.convertirAfiliadoModificacionDirectivaModel()) }
    return lista
}
//endregion